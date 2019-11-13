package com.example.myapplication.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.modal.DeleteResponse;
import com.example.myapplication.modal.Employee;
import com.example.myapplication.modal.Employee2;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class EmployeeRepository implements EmployeeDataSource {

    private final EmployeeServices employeeServices;
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    @Inject
    EmployeeRepository(EmployeeServices employeeServices) {
        this.employeeServices = employeeServices;
    }

    @Override
    public LiveData<Boolean> getLoadingStatus() {
        return isLoading;
    }

    @Override
    public LiveData<List<Employee>> getAllEmployee() {
        MutableLiveData<List<Employee>> employeeList = new MutableLiveData<>();
        isLoading.setValue(true);
        employeeServices.getAllEmployee().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful()) {
                    employeeList.setValue(response.body());
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                employeeList.setValue(null);
                isLoading.setValue(false);
            }
        });
        return employeeList;
    }

    @Override
    public LiveData<Employee> getEmployeeById(String employeeId) {

        MutableLiveData<Employee> employee = new MutableLiveData<>();
        isLoading.setValue(true);
        employeeServices.getEmployeeById(employeeId).enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {
                    employee.setValue(response.body());
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                employee.setValue(null);
                isLoading.setValue(false);
            }
        });
        return employee;
    }

    @Override
    public LiveData<Employee2> addNewEmployee(Employee2 employee) {
        MutableLiveData<Employee2> employeeResponse = new MutableLiveData<>();
        isLoading.setValue(true);
        employeeServices.addEmployee(employee).enqueue(new Callback<Employee2>() {
            @Override
            public void onResponse(Call<Employee2> call, Response<Employee2> response) {
                if (response.isSuccessful()) {
                    employeeResponse.setValue(response.body());
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<Employee2> call, Throwable t) {
                employeeResponse.setValue(null);
                isLoading.setValue(false);
            }
        });
        return employeeResponse;
    }

    @Override
    public LiveData<Employee2> updateEmployee(Employee2 employee) {
        MutableLiveData<Employee2> employeeResponse = new MutableLiveData<>();
        isLoading.setValue(true);
        employeeServices.updateEmployee(employee.getId(), employee).enqueue(new Callback<Employee2>() {
            @Override
            public void onResponse(Call<Employee2> call, Response<Employee2> response) {
                if (response.isSuccessful()) {
                    employeeResponse.setValue(response.body());
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<Employee2> call, Throwable t) {
                employeeResponse.setValue(null);
                isLoading.setValue(false);
            }
        });
        return employeeResponse;
    }

    @Override
    public LiveData<DeleteResponse> deleteEmployee(String employeeId) {
        MutableLiveData<DeleteResponse> deleteResponse = new MutableLiveData<>();
        isLoading.setValue(true);
        employeeServices.deleteEmployee(employeeId).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                if (response.isSuccessful()) {
                    deleteResponse.setValue(response.body());
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                deleteResponse.setValue(null);
                isLoading.setValue(false);
            }
        });
        return deleteResponse;
    }
}

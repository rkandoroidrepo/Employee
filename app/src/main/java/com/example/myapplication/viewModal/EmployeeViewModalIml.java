package com.example.myapplication.viewModal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.EmployeeDataSource;
import com.example.myapplication.modal.DeleteResponse;
import com.example.myapplication.modal.Employee;
import com.example.myapplication.modal.Employee2;

import java.util.List;

import javax.inject.Inject;

public class EmployeeViewModalIml extends ViewModel implements EmployeeViewModal {

    private final EmployeeDataSource repository;
    private LiveData<List<Employee>> employeeList;

    @Inject
    EmployeeViewModalIml(EmployeeDataSource repository) {
        this.repository = repository;
    }

    @Override
    public LiveData<Boolean> getLoadingStatus() {
        return repository.getLoadingStatus();
    }

    @Override
    public LiveData<List<Employee>> getEmployee(boolean fetchNew) {
        if (fetchNew) {
            employeeList = repository.getAllEmployee();
        } else {
            if (employeeList == null) {
                employeeList = repository.getAllEmployee();
            }
        }
        return employeeList;

    }

    @Override
    public LiveData<Employee> getEmployee(String id) {
        return repository.getEmployeeById(id);
    }

    @Override
    public LiveData<Employee2> addNewEmployee(Employee2 employee) {
        return repository.addNewEmployee(employee);
    }

    @Override
    public LiveData<Employee2> updateEmployee(Employee2 employee) {
        return repository.updateEmployee(employee);
    }

    @Override
    public LiveData<DeleteResponse> deleteEmployee(String id) {
        return repository.deleteEmployee(id);
    }
}

package com.example.myapplication.viewModal;

import androidx.lifecycle.LiveData;

import com.example.myapplication.modal.DeleteResponse;
import com.example.myapplication.modal.Employee;
import com.example.myapplication.modal.Employee2;

import java.util.List;

public interface EmployeeViewModal {

    LiveData<Boolean> getLoadingStatus();

    LiveData<List<Employee>> getEmployee(boolean fetchNew);

    LiveData<Employee> getEmployee(String id);

    LiveData<Employee2> addNewEmployee(Employee2 employee);

    LiveData<Employee2> updateEmployee(Employee2 employee);

    LiveData<DeleteResponse> deleteEmployee(String id);
}

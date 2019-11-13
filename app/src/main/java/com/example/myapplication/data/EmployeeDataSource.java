package com.example.myapplication.data;

import androidx.lifecycle.LiveData;

import com.example.myapplication.modal.DeleteResponse;
import com.example.myapplication.modal.Employee;
import com.example.myapplication.modal.Employee2;

import java.util.List;

public interface EmployeeDataSource {

    /**
     * Get the loading status
     * @return true if any service is loading else false
     */
    LiveData<Boolean> getLoadingStatus();

    /**
     * Gets all the employee list
     * @return employee list
     */
    LiveData<List<Employee>> getAllEmployee();

    /**
     * Gets employee by using id
     * @param employeeId employee id
     * @return employee
     */
    LiveData<Employee> getEmployeeById(String employeeId);

    /**
     * Create new employee
     * @param employee employee body
     * @return
     */
    LiveData<Employee2> addNewEmployee(Employee2 employee);

    /**
     * Update employee details
     * @param employee
     * @return updated details
     */
    LiveData<Employee2> updateEmployee(Employee2 employee);

    /**
     * Delete employee details
     * @param employeeId id
     * @return response
     */
    LiveData<DeleteResponse> deleteEmployee(String employeeId);
}

package com.example.myapplication.data;

import com.example.myapplication.modal.DeleteResponse;
import com.example.myapplication.modal.Employee;
import com.example.myapplication.modal.Employee2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeServices {

    @GET("employees")
    Call<List<Employee>> getAllEmployee();

    @GET("employee/{id}")
    Call<Employee> getEmployeeById(@Path("id") String id);

    @POST("create")
    Call<Employee2> addEmployee(@Body Employee2 employee);

    @PUT("update/{id}")
    Call<Employee2> updateEmployee(@Path("id") String id, @Body Employee2 employee);

    @DELETE("delete/{id}")
    Call<DeleteResponse> deleteEmployee(@Path("id") String id);

}

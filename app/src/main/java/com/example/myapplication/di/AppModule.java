package com.example.myapplication.di;

import com.example.myapplication.data.EmployeeDataSource;
import com.example.myapplication.data.EmployeeRepository;
import com.example.myapplication.data.EmployeeServices;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static EmployeeServices provideEmployeeServices(Retrofit retrofit) {
        return retrofit.create(EmployeeServices.class);
    }

    @Singleton
    @Provides
    EmployeeDataSource provideImageRepository(EmployeeRepository employeeRepository) {
        return employeeRepository;
    }
}

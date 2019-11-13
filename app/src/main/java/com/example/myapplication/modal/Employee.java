package com.example.myapplication.modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employee implements Parcelable {

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };
    @SerializedName("employee_name")
    @Expose
    private String name;
    @SerializedName("employee_salary")
    @Expose
    private String salary;
    @SerializedName("employee_age")
    @Expose
    private String age;
    @SerializedName("id")
    @Expose
    private String id;

    public Employee() {
    }

    private Employee(Parcel in) {
        name = in.readString();
        salary = in.readString();
        age = in.readString();
        id = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(salary);
        dest.writeString(age);
        dest.writeString(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }
}

package com.example.cantinappmobile.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Turn {
    @SerializedName("id")
    int id;
    @SerializedName("day")
    String day;
    @SerializedName("period")
    int period;
    @SerializedName("class")
    String employeeClass;
    List<Employee> employeeList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getEmployeeClass() {
        return employeeClass;
    }

    public void setEmployeeClass(String employeeClass) {
        this.employeeClass = employeeClass;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public String getLiteralPeriod() {
        if (period ==1){
            return "Manhã";
        }else if (period == 2){
            return "Tarde";
        }
        return "";
    }
}

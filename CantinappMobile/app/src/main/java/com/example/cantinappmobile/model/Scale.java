package com.example.cantinappmobile.model;

import com.example.cantinappmobile.resources.DateFormatter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Scale {
    @SerializedName("id")
    int id;
    @SerializedName("day")
    String day;
    @SerializedName("period")
    String period;
    @SerializedName("class")
    String _class;
    @SerializedName("employee_array")
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

    public String getFormatedDate() {
        return DateFormatter.dateFormat(day);
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}

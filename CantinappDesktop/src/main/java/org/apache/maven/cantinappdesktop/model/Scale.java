package org.apache.maven.cantinappdesktop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Scale {
    @SerializedName("id")
    private int id;
    @SerializedName("class")
    private String _class;
    @SerializedName("day")
    private String day;
    @SerializedName("period")
    private String period;
    @SerializedName("employee_array")
    private List<Employee> employeeList;
    private String employeeNamesString = "";

    public String getEmployeeNamesString() {
        return employeeNamesString;
    }

    public void setEmployeeNamesString(String employeeNamesString) {
        this.employeeNamesString = employeeNamesString;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Scale(int id, String _class, String day, String period, List<Employee> employeeList) {
        this.id = id;
        this._class = _class;
        this.day = day;
        this.period = period;
        this.employeeList = employeeList;
    }

    public int getId() {
        return id;
    }

    public String get_class() {
        return _class;
    }

    public String getPeriod() {
        return period;
    }

    public String getDay() {
        return day;
    }

    public void createNameString() {
        String allEmployeeNames = "";
        for (Employee employee :
                employeeList) {
            if (allEmployeeNames.equals("")) {
                allEmployeeNames = employee.getName();
            } else {
                allEmployeeNames = allEmployeeNames + ", " + employee.getName();
            }
        }
        this.employeeNamesString = allEmployeeNames;
    }
}

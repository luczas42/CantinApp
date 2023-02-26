package org.apache.maven.cantinappdesktop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Scale {
    @SerializedName("id")
    private int turnId;
    @SerializedName("class")
    private String _class;
    @SerializedName("day")
    private String day;
    @SerializedName("period")
    private String period;
    /// period = 0 means morning
    /// period = 1 means afternoon
    /// period = 2 means night
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

    public Scale(int turnId, String _class, String day, String period, List<Employee> employeeList) {
        this.turnId = turnId;
        this._class = _class;
        this.day = day;
        this.period = period;
        this.employeeList = employeeList;
    }

    public Scale(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public int getTurnId() {
        return turnId;
    }

    public void setTurnId(int turnId) {
        this.turnId = turnId;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void createNameString() {
        String allEmployeeNames = "";
        for (Employee employee :
                employeeList) {
            if(allEmployeeNames.equals("")){
                allEmployeeNames = employee.getName();
            }else{
                allEmployeeNames = allEmployeeNames + ", " + employee.getName();
            }
        }
        this.employeeNamesString = allEmployeeNames;
    }

    public void formatDay() {

    }
}

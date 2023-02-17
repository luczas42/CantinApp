package org.apache.maven.cantinappdesktop.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Scale {

    private List<String> employeeNamesString;
    @SerializedName("id")
    private int turn_id;
    @SerializedName("class")
    private String clasS;
    /// clasS is used as a substitute for the word 'class', which is marked as a key word
    @SerializedName("day")
    private String day;
    @SerializedName("period")
    private int period;
    /// period = 0 means morning
    /// period = 1 means afternoon
    /// period = 2 means night
    @SerializedName("employee_array")
    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Scale(int turn_id, String clasS, String day, int period, List<Employee> employeeList) {
        this.turn_id = turn_id;
        this.clasS = clasS;
        this.day = day;
        this.period = period;
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Scale{" +
                "turn_id=" + turn_id +
                ", clasS='" + clasS + '\'' +
                ", date='" + day + '\'' +
                ", period=" + period +
                ", employeeList=" + employeeList +
                '}';
    }

    public Scale(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public int getTurn_id() {
        return turn_id;
    }

    public void setTurn_id(int turn_id) {
        this.turn_id = turn_id;
    }

    public String getClasS() {
        return clasS;
    }

    public void setClasS(String clasS) {
        this.clasS = clasS;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void createNameString(){
        List<String> auxList = new ArrayList<>();
        for (Employee employee :
                employeeList) {
            auxList.add(employee.getName());
        }
        setEmployeeNamesString(auxList);
    }

    public List<String> getEmployeeNamesString() {
        return employeeNamesString;
    }

    public void setEmployeeNamesString(List<String> employeeNamesString) {
        this.employeeNamesString = employeeNamesString;
    }
    public void formatDay(){

    }
}

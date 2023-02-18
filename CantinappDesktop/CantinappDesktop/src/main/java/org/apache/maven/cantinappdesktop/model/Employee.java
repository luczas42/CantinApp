package org.apache.maven.cantinappdesktop.model;

import com.google.gson.annotations.SerializedName;

public class Employee {

    @SerializedName("id")
    private int employeeId;
    @SerializedName("id_scale")
    private int scaleId;
    @SerializedName("name")
    private String name;
    @SerializedName("class")
    private String clasS;

    public Employee(String name, String clasS) {
        this.name = name;
        this.clasS = clasS;
    }

    public Employee(int employeeId, int scaleId, String name) {
        this.employeeId = employeeId;
        this.scaleId = scaleId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasS() {
        return clasS;
    }

    public void setClasS(String clasS) {
        this.clasS = clasS;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getScaleId() {
        return scaleId;
    }

    public void setScaleId(int scaleId) {
        this.scaleId = scaleId;
    }
}

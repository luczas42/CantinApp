package org.apache.maven.cantinappdesktop.model;

import com.google.gson.annotations.SerializedName;

public class Employee {

    @SerializedName("id")
    private int employee_Id;
    @SerializedName("id_scale")
    private int scale_id;
    @SerializedName("name")
    private String name;
    @SerializedName("class")
    private String clasS;

    public Employee(String name, String clasS) {
        this.name = name;
        this.clasS = clasS;
    }

    public Employee(int employee_Id, int scale_id, String name, String clasS) {
        this.employee_Id = employee_Id;
        this.scale_id = scale_id;
        this.name = name;
        this.clasS = clasS;
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

    public int getEmployee_Id() {
        return employee_Id;
    }

    public void setEmployee_Id(int employee_Id) {
        this.employee_Id = employee_Id;
    }

    public int getScale_id() {
        return scale_id;
    }

    public void setScale_id(int scale_id) {
        this.scale_id = scale_id;
    }
}

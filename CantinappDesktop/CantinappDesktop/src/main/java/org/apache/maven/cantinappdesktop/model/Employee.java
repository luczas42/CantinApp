package org.apache.maven.cantinappdesktop.model;

import com.google.gson.annotations.SerializedName;

public class Employee {

    @SerializedName("name")
    private String name;

    @SerializedName("class")
    private String clasS;

    public Employee(String name, String clasS) {
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
}

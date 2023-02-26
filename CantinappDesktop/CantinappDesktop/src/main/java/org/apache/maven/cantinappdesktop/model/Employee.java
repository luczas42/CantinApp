package org.apache.maven.cantinappdesktop.model;

import com.google.gson.annotations.SerializedName;

public class Employee {
    @SerializedName("id")
    private int id;
    @SerializedName("scale_id")
    private int scaleId;
    @SerializedName("name")
    private String name;
    @SerializedName("class")
    private String _class;

    public Employee(int id, int scaleId, String name, String _class) {
        this.id = id;
        this.scaleId = scaleId;
        this.name = name;
        this._class = _class;
    }

    public Employee(int id, String name, String _class) {
        this.id = id;
        this.name = name;
        this._class = _class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasS() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScaleId() {
        return scaleId;
    }

    public void setScaleId(int scaleId) {
        this.scaleId = scaleId;
    }
}

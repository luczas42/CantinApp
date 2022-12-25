package org.apache.maven.cantinappdesktop.data.service;


import com.google.gson.annotations.SerializedName;

public class Products {
    @SerializedName("name")
    public String name;
    @SerializedName("price")
    public Float price;

    public Products(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

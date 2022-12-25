package com.example.cantinappmobile.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("name")
    String name;
    @SerializedName("price")
    Float price;

    public Product(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

package com.example.cantinappmobile.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("name")
    String name;
    @SerializedName("price")
    Float price;
    @SerializedName("image")
    byte[] image;

    public Product(String name, Float price, byte[] image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Product(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

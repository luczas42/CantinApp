package com.example.cantinappmobile.model;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("name")
    String name;
    @SerializedName("price")
    Float price;
    @SerializedName("image")
    String image;

    byte[] imageView;

    public Product(String name, Float price, String image, byte[] imageView) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.imageView = imageView;
    }

    public Product(String name, Float price, byte[] imageView) {
        this.name = name;
        this.price = price;
        this.imageView = imageView;
    }

    public byte[] getImageView() {
        return imageView;
    }

    public void setImageView(byte[] imageView) {
        this.imageView = imageView;
    }

    public Product(String name, Float price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Product(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

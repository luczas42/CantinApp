package org.apache.maven.cantinappdesktop.model;


import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Product {

    @SerializedName("id")
    private int id;
    @SerializedName("image")
    private File image;


    private byte[] imageFromServer;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private Float price;

    public Product(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, Float price, File image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public byte[] getImageFromServer() {
        return imageFromServer;
    }

    public void setImageFromServer(byte[] imageFromServer) {
        this.imageFromServer = imageFromServer;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }


}

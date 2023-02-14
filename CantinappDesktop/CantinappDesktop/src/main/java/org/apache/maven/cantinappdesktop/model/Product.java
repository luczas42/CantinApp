package org.apache.maven.cantinappdesktop.model;


import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Product {

    @SerializedName("id")
    private int id;
    private File imageFile;

    @SerializedName("image")
    private String imageName;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private Float price;

    public Product(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, Float price, File imageFile) {
        this.name = name;
        this.price = price;
        this.imageFile = imageFile;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFromServer() {
        return imageName;
    }

    public void setImageFromServer(String imageName) {
        this.imageName = imageName;
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

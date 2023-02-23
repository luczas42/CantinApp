package org.apache.maven.cantinappdesktop.model;


import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Product {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String imageName;
    @SerializedName("price")
    private Float price;
    @SerializedName("productType")
    private String productType;
    private File imageFile;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Product(String name, Float price, String productType, File imageFile) {
        this.name = name;
        this.price = price;
        this.productType = productType;
        this.imageFile = imageFile;
    }

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

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
    private File imageFile;
    private String formattedPrice;
    private byte[] imageFromServer;
    @SerializedName("productType")
    private int productType;

    public void setFormattedPrice(String formattedPrice) {
        this.formattedPrice = formattedPrice;
    }

    public String getFormattedPrice() {
        return formattedPrice;
    }

    public Product(String name, Float price, int productType) {
        this.name = name;
        this.price = price;
        this.productType = productType;
    }

    public int getProductType() {
        return productType;
    }

    public String getLiteralProductType() {
        return switch (getProductType()) {
            case 1 -> "Salgado";
            case 2 -> "Doce";
            case 3 -> "Caseiro";
            default -> "Vazio";
        };
    }

    public Product(String name, Float price, int productType, File imageFile) {
        this.name = name;
        this.price = price;
        this.productType = productType;
        this.imageFile = imageFile;
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


    public void setId(int id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }


}

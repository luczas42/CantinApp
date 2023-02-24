package org.apache.maven.cantinappdesktop.model;


import com.google.gson.annotations.SerializedName;

import java.io.File;

public class Product {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @SerializedName("image")
    private String imageName;
    @SerializedName("price")
    private Float price;
    @SerializedName("productType")
    private int productType;

    private byte[] imageFromServer;

    public void setImageFromServer(byte[] imageFromServer) {
        this.imageFromServer = imageFromServer;
    }

    public Product(int id, String name, Float price, int productType, byte[] imageFromServer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productType = productType;
        this.imageFromServer = imageFromServer;
    }

    private File imageFile;

    public int getProductType() {
        return productType;
    }

    public String getLiteralProductType(){
        return switch (getProductType()) {
            case 1 -> "Salgado";
            case 2 -> "Doce";
            case 3 -> "Caseiro";
            default -> "Vazio";
        };
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public Product(String name, Float price, int productType, File imageFile) {
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

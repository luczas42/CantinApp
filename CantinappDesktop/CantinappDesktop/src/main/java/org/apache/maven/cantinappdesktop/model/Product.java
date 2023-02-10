package org.apache.maven.cantinappdesktop.model;


import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    private int id;
    @SerializedName("image")
    private byte[] image;
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private Float price;

    public Product(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, Float price, byte[] image) {
        this.name = name;
        this.price = price;
        this.image = image;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

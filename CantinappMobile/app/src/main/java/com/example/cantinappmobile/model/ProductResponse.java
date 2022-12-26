package com.example.cantinappmobile.model;

import java.util.ArrayList;

public class ProductResponse {

    ArrayList<Product> productList;

    public ProductResponse(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }
}

package com.example.cantinappmobile.model;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse {

    List<Product> productList;

    public ProductResponse(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }
}

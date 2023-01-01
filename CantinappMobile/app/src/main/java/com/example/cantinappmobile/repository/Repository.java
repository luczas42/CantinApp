package com.example.cantinappmobile.repository;

import com.example.cantinappmobile.model.Product;

import java.util.List;

public interface Repository {

    List<Product> retrieveProductsFromWebService();

}

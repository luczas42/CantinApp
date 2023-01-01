package com.example.cantinappmobile.repository;

import com.example.cantinappmobile.model.Product;

import java.util.List;

import retrofit2.Call;

public interface Repository {

    Call<List<Product>> retrieveProductsFromWebService();

}

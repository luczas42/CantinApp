package com.example.cantinappmobile.repository;

import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.model.User;

import java.util.List;

import retrofit2.Call;

public interface Repository {

    Call<List<Product>> retrieveProductsFromWebService();

    Call<User> userLogin(String username, String password);

}

package com.example.cantinappmobile.repository;

import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.retrofit.ClientRetrofit;
import com.example.cantinappmobile.retrofit.WebService;

import java.util.List;

import retrofit2.Call;

public class RepositoryImpl implements Repository {

    private final WebService
            webService = ClientRetrofit.getInstance().create(WebService.class);

    @Override
    public Call<List<Product>> retrieveProductsFromWebService() {
        return webService.retrieveProducts();
    }

}
package com.example.cantinappmobile.repository;

import com.example.cantinappmobile.model.ProductResponse;
import com.example.cantinappmobile.retrofit.ClientRetrofit;
import com.example.cantinappmobile.retrofit.WebService;

import retrofit2.Response;

public class RepositoryImpl implements Repository {

    private final WebService
            webService = ClientRetrofit.getInstance().create(WebService.class);

    @Override
    public ProductResponse retrieveProducts() {

        try {
            Response<ProductResponse> response = webService.retrieveProducts();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

}

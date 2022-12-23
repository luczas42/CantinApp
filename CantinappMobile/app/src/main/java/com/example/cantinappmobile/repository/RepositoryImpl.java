package com.example.cantinappmobile.repository;

import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.model.ProductResponse;
import com.example.cantinappmobile.retrofit.WebService;

import retrofit2.Response;

public class RepositoryImpl implements Repository {

    WebService webService;

    public RepositoryImpl(WebService webService) {
        this.webService = webService;
    }

    @Override
    public ProductResponse retrieveProducts() {

        try {
            Response<ProductResponse> response = webService.retrieveProducts();
            if (response.isSuccessful()) {
                return response.body();
            }else{
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

}

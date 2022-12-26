package com.example.cantinappmobile.repository;

import android.util.Log;

import com.example.cantinappmobile.model.ProductResponse;
import com.example.cantinappmobile.retrofit.ClientRetrofit;
import com.example.cantinappmobile.retrofit.WebService;

import retrofit2.Response;

public class RepositoryImpl implements Repository {

    private final WebService
            webService = ClientRetrofit.getInstance().create(WebService.class);

    @Override
    public ProductResponse retrieveProductsFromWebService() {
        try {
            Response<ProductResponse> response = webService.retrieveProducts();
            if (response.isSuccessful()) {
                Log.i("logLogin", "retrieveProducts: response success!");
                return response.body();
            } else {
                Log.i("logLogin", "retrieveProducts: response failed!");
                return null;
            }
        } catch (Exception e) {
            Log.i("logLogin", "retrieveProducts: request failed!");
            return null;
        }
    }

}

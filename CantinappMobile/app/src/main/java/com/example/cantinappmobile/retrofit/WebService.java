package com.example.cantinappmobile.retrofit;

import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.model.ProductResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebService {

    @GET("getProducts.php")
    Response<ProductResponse> retrieveProducts();


}

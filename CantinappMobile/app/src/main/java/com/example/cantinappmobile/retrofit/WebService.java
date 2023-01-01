package com.example.cantinappmobile.retrofit;

import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.model.ProductResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebService {

    @POST("getProducts.php")
    Call<List<Product>> retrieveProducts();


}

package com.example.cantinappmobile.retrofit;

import com.example.cantinappmobile.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface WebService {

    @POST("getProducts.php")
    Call<List<Product>> retrieveProducts();


}

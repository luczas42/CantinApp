package com.example.cantinappmobile.retrofit;

import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WebService {

    @POST("products/getProducts.php")
    Call<List<Product>> retrieveProducts();

    @FormUrlEncoded
    @POST("users/login.php")
    Call<User> userLogin(@Field("username") String username, @Field("password")String password);

}

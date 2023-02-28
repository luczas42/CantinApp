package com.example.cantinappmobile.retrofit;

import com.example.cantinappmobile.model.Employee;
import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.model.Scale;
import com.example.cantinappmobile.model.User;
import com.example.cantinappmobile.model.UserApiReturn;

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
    Call<List<User>> userLogin(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("users/addUser.php")
    Call<UserApiReturn> addUser(@Field("username")String username, @Field("name") String name, @Field("password") String password, @Field("isUser") int isUser, @Field("email") String email );

    @POST("scales/getScale.php")
    Call<List<Scale>> getScales();
}

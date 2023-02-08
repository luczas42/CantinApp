package org.apache.maven.cantinappdesktop.retrofit;


import org.apache.maven.cantinappdesktop.model.Products;
import org.apache.maven.cantinappdesktop.model.Users;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;

public interface ApiService {
    @POST("products/getProducts.php")
    Call<List<Products>> getProducts();


    @FormUrlEncoded
    @POST("products/editProduct.php")
    Call<Products> editProduct(@Field("name") String name,
                               @Field("price") Float price,
                               @Field("id") int id);

    @FormUrlEncoded
    @POST("products/addProduct.php")
    Call<Products> addProduct(@Field("name") String name,
                              @Field("price") Float price);

    @FormUrlEncoded
    @POST("products/deleteProduct.php")
    Call<Void> deleteProduct(@Field("id") int id);

    @FormUrlEncoded
    @POST("users/addUser.php")
    Call<Users> addUser(@Field("username") String username,
                        @Field("name") String name,
                        @Field("password") String password,
                        @Field("isUser") int isUser,
                        @Field("email") String email);

    @FormUrlEncoded
    @POST("users/login.php")
    Call<Users> userLogin(@Field("username") String username, @Field("password") String password);
}
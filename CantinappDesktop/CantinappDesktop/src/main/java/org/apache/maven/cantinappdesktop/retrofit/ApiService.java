package org.apache.maven.cantinappdesktop.retrofit;


import org.apache.maven.cantinappdesktop.model.Product;
import org.apache.maven.cantinappdesktop.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;

public interface ApiService {
    @POST("products/getProducts.php")
    Call<List<Product>> getProducts();


    @FormUrlEncoded
    @POST("products/editProduct.php")
    Call<Product> editProduct(@Field("name") String name,
                              @Field("price") Float price,
                              @Field("id") int id);

    @FormUrlEncoded
    @POST("products/addProduct.php")
    Call<Product> addProduct(@Field("name") String name,
                             @Field("price") Float price,
                             @Field("byte_array") byte[] image);

    @FormUrlEncoded
    @POST("products/deleteProduct.php")
    Call<Void> deleteProduct(@Field("id") int id);

    @FormUrlEncoded
    @POST("users/addUser.php")
    Call<User> addUser(@Field("username") String username,
                       @Field("name") String name,
                       @Field("password") String password,
                       @Field("isUser") int isUser,
                       @Field("email") String email);

    @FormUrlEncoded
    @POST("users/login.php")
    Call<User> userLogin(@Field("username") String username, @Field("password") String password);
}

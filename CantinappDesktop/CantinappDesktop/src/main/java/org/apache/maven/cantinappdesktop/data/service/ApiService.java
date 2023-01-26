package org.apache.maven.cantinappdesktop.data.service;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;

public interface ApiService {
    @POST("getProducts.php")
    Call<List<Products>> getProducts();


    @FormUrlEncoded
    @POST("editProduct.php")
    Call<Products> editProduct(@Field("name") String name, @Field("price") Float price, @Field("id") int id);

    @FormUrlEncoded
    @POST("addProduct.php")
    Call<Products> addProduct(@Field("name") String name, @Field("price") Float price);

    @FormUrlEncoded
    @POST("deleteProduct.php")
    Call<Void> deleteProduct(@Field("id") int id);


}

package org.apache.maven.cantinappdesktop.retrofit;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.maven.cantinappdesktop.model.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApiService {
    @POST("products/getProducts.php")
    Call<List<Product>> getProducts();

    @Multipart
    @POST("products/getProductImage.php")
    Call<ResponseBody> getImage(@Part("image_name") RequestBody imageName);

    @POST("employees/getEmployees.php")
    Call<List<Employee>> getEmployees();


    @FormUrlEncoded
    @POST("products/editProduct.php")
    Call<Product> editProduct(@Field("name") String name,
                              @Field("price") Float price,
                              @Field("id") int id);

    @POST("products/addProduct.php")
    @Multipart
    Call<Product> addProduct(@Part("pname") RequestBody name,
                             @Part("price") RequestBody price,
                             @Part MultipartBody.Part image);

    @POST("products/addProduct.php")
    @Multipart
    Call<Product> addProduct(@Part("pname") RequestBody name,
                             @Part("price") RequestBody price);

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

    @POST("employees/addEmployee.php")
    @Multipart
    Call<Employee> addEmployee(@Part("name") RequestBody name,
                           @Part("class") RequestBody clasS);

    @FormUrlEncoded
    @POST("scales/getScale.php")
    Call<List<Employee>> getScalesEmployee(@Field("turn_id") int turn_id);

    @POST("turns/getTurns.php")
    Call<List<Scale>> getTurns();
}

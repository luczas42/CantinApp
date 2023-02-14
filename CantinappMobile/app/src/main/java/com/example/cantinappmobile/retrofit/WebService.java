package com.example.cantinappmobile.retrofit;

import com.example.cantinappmobile.model.Employee;
import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.model.Turn;
import com.example.cantinappmobile.model.User;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface WebService {

    @POST("products/getProducts.php")
    Call<List<Product>> retrieveProducts();

    @Multipart
    @POST("products/getProductImage.php")
    Call<ResponseBody> getImage(@Part("image_name") RequestBody imageName);

    @FormUrlEncoded
    @POST("users/login.php")
    Call<List<User>> userLogin(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("users/addUser.php")
    Call<User> addUser(@Field("username")String username, @Field("name") String name, @Field("password") String password, @Field("isUser") int isUser, @Field("email") String email );

    @POST("turn/getTurns.php")
    Call<List<Turn>> getTurns();

    @FormUrlEncoded
    @POST("scales/getScale.php")
    Call<List<Employee>> getTurnEmployees(@Field("turn_id") int turnId);
}

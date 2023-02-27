package org.apache.maven.cantinappdesktop.retrofit;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.apache.maven.cantinappdesktop.model.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApiService {
    @POST("products/getProducts.php")
    Call<List<Product>> getProducts();

    @POST("employees/getEmployees.php")
    Call<List<Employee>> getEmployees();


    @Multipart
    @POST("products/editProduct.php")
    Call<ApiResponse> editProduct(@Part("pname") RequestBody name,
                                  @Part("price") RequestBody price,
                                  @Part("productType") RequestBody productType,
                                  @Part("pid") RequestBody pid
    );

    @Multipart
    @POST("products/editProduct.php")
    Call<ApiResponse> editProduct(@Part("pname") RequestBody name,
                              @Part("price") RequestBody price,
                              @Part("productType") RequestBody productType,
                              @Part("pid") RequestBody pid,
                              @Part MultipartBody.Part image);

    @POST("products/addProduct.php")
    @Multipart
    Call<Product> addProduct(@Part("pname") RequestBody name,
                             @Part("price") RequestBody price,
                             @Part("productType") RequestBody productType,
                             @Part MultipartBody.Part image);

    @POST("products/addProduct.php")
    @FormUrlEncoded
    Call<Product> addProduct(@Field("pname") String name,
                             @Field("price") Float price,
                             @Field("productType") int productType);

    @FormUrlEncoded
    @POST("products/deleteProduct.php")
    Call<Void> deleteProduct(@Field("id") int id);

    @FormUrlEncoded
    @POST("users/addUser.php")
    Call<ApiResponse> addUser(@Field("username") String username,
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

    @POST("scales/getScale.php")
    Call<List<Scale>> getScales();

    @FormUrlEncoded
    @POST("employees/getEmployeesWithClass.php")
    Call<List<Employee>> getEmployeesWithClass(@Field("class") String clasS);

    @FormUrlEncoded
    @POST("scales/addScale.php")
    Call<Scale> addScale(@Field("day") String day,
                         @Field("period") String period,
                         @Field("class") String _class,
                         @Field("employeeArray[]") List<Integer> employees);

    @FormUrlEncoded
    @POST("employees/editEmployee.php")
    Call<Employee> editEmployee(@Field("id") int employeeId,
                                @Field("name") String employeeName,
                                @Field("class") String employeeClass);

    @FormUrlEncoded
    @POST("employees/deleteEmployee.php")
    Call<Void> deleteEmployee(@Field("id") int id);

    @FormUrlEncoded
    @POST("turns/deleteTurn.php")
    Call<Void> deleteScale(@Field("id") int turnId);
}

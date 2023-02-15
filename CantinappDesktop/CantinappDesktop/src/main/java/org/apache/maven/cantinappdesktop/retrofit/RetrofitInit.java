package org.apache.maven.cantinappdesktop.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.maven.cantinappdesktop.model.*;
import org.apache.maven.cantinappdesktop.util.FileTypeAdapter;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.util.List;

public class RetrofitInit {

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(File.class, new FileTypeAdapter())
            .create();
    Retrofit retrofit = (new Retrofit.Builder()).baseUrl("http://54.207.241.251/CantinappServer/").addConverterFactory(GsonConverterFactory.create(gson)).build();
    ApiService apiService;

    /// RETROFIT INSTANCE

    public RetrofitInit() {
        this.apiService = (ApiService) this.retrofit.create(ApiService.class);
    }

    /// GETS

    public void getProducts(Callback<List<Product>> callback) {
        this.apiService.getProducts().enqueue(callback);
    }

    public void getImage(Callback<ResponseBody> callback, RequestBody imageName){
        this.apiService.getImage(imageName).enqueue(callback);
    }

    public void getEmployees(Callback<List<Employee>> callback) {
        this.apiService.getEmployees().enqueue(callback);
    }

    public void getScalesEmployee(Callback<List<Employee>> callback, int turn_id){
        this.apiService.getScalesEmployee(turn_id).enqueue(callback);
    }

    public void getScalesTurn(Callback<List<Scale>> callback){
        this.apiService.getTurns().enqueue(callback);
    }

    /// INSERTS

    public void addProducts(Callback<Product> call, RequestBody name, RequestBody price, MultipartBody.Part file) {
        this.apiService.addProduct(name, price, file).enqueue(call);

    }

    public void addProducts(Callback<Product> call, RequestBody name, RequestBody price) {
        this.apiService.addProduct(name, price).enqueue(call);
    }

    public void addUser(Callback<User> call, User user, String password) {
        this.apiService.addUser(user.getUsername(),
                        user.getName(),
                        password,
                        user.getIsUser(),
                        user.getEmail())
                .enqueue(call);
    }

    public void addEmployee(Callback<Employee> call, RequestBody name, RequestBody clasS){
        this.apiService.addEmployee(name, clasS).enqueue(call);
    }

    /// EDITS

    public void editProducts(Callback<Product> call, Product product) {
        this.apiService.editProduct(product.getName(), product.getPrice(), product.getId()).enqueue(call);
    }

    /// DELETES

    public void deleteProduct(Callback<Void> call, int id) {
        this.apiService.deleteProduct(id).enqueue(call);
    }

    /// VERIFICATION

    public void checkLogin(Callback<User> call, String username, String password) {
        this.apiService.userLogin(username, password).enqueue(call);
    }

}

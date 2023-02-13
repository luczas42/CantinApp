package org.apache.maven.cantinappdesktop.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.apache.maven.cantinappdesktop.model.Product;
import org.apache.maven.cantinappdesktop.model.User;
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

    public RetrofitInit() {
        this.apiService = (ApiService) this.retrofit.create(ApiService.class);
    }

    public void getProducts(Callback<List<Product>> callback) {
        this.apiService.getProducts().enqueue(callback);
    }

    public void addProducts(Callback<Product> call, RequestBody name, RequestBody price, MultipartBody.Part file) {
        this.apiService.addProduct(name, price, file).enqueue(call);

    }
    public void addProducts(Callback<Product> call, RequestBody name, RequestBody price) {
        this.apiService.addProduct(name, price).enqueue(call);

    }

    public void editProducts(Callback<Product> call, Product product) {
        this.apiService.editProduct(product.getName(), product.getPrice(), product.getId()).enqueue(call);
    }

    public void deleteProduct(Callback<Void> call, int id) {
        this.apiService.deleteProduct(id).enqueue(call);
    }

    // USUARIO
    public void addUser(Callback<User> call, User user, String password) {
        this.apiService.addUser(user.getUsername(),
                        user.getName(),
                        password,
                        user.getIsUser(),
                        user.getEmail())
                .enqueue(call);
    }

    public void checkLogin(Callback<User> call, String username, String password) {
        this.apiService.userLogin(username, password).enqueue(call);
    }


}

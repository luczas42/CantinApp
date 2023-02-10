package org.apache.maven.cantinappdesktop.retrofit;

import org.apache.maven.cantinappdesktop.model.Product;
import org.apache.maven.cantinappdesktop.model.User;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class RetrofitInit {
    Retrofit retrofit = (new Retrofit.Builder()).baseUrl("https://cantinapp.000webhostapp.com/cantinapp/").addConverterFactory(GsonConverterFactory.create()).build();
    ApiService apiService;

    public RetrofitInit() {
        this.apiService = (ApiService) this.retrofit.create(ApiService.class);
    }

    public void getProducts(Callback<List<Product>> callback) {
        this.apiService.getProducts().enqueue(callback);
    }

    public void addProducts(Callback<Product> call, Product product) {
        this.apiService.addProduct(product.getName(), product.getPrice(), product.getImage()).enqueue(call);

    }

    public void editProducts(Callback<Product> call, Product product) {
        this.apiService.editProduct(product.getName(), product.getPrice(), product.getId()).enqueue(call);
    }

    public void deleteProduct(Callback<Void> call, int id) {
        this.apiService.deleteProduct(id).enqueue(call);
    }

    // USUARIO
    public void addUser(Callback<User> call, User user, String password){
        this.apiService.addUser(user.getUsername(),
                        user.getName(),
                        password,
                        user.getIsUser(),
                        user.getEmail())
                .enqueue(call);
    }

    public void checkLogin(Callback<User> call, String username, String password){
        this.apiService.userLogin(username, password).enqueue(call);
    }


}

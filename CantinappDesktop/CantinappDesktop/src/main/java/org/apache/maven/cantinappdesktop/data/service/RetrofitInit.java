package org.apache.maven.cantinappdesktop.data.service;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.List;

public class RetrofitInit {
    Retrofit retrofit = (new Retrofit.Builder()).baseUrl("http://54.94.3.48/testephp/").addConverterFactory(GsonConverterFactory.create()).build();
    ApiService apiService;

    public RetrofitInit() {
        this.apiService = (ApiService)this.retrofit.create(ApiService.class);
    }

    public void getProducts(Callback<List<Products>> callback) {
        this.apiService.getProducts().enqueue(callback);
    }
}

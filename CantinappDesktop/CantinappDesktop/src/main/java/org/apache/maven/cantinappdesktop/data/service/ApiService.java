package org.apache.maven.cantinappdesktop.data.service;


import retrofit2.Call;
import retrofit2.http.POST;

import java.util.List;

public interface ApiService {
    @POST("getProducts.php")
    Call<List<Products>> getProducts();
}

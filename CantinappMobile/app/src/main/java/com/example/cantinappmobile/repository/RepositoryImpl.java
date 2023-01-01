package com.example.cantinappmobile.repository;

import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.retrofit.ClientRetrofit;
import com.example.cantinappmobile.retrofit.WebService;

import java.util.List;

import retrofit2.Call;

public class RepositoryImpl implements Repository {

    private final WebService
            webService = ClientRetrofit.getInstance().create(WebService.class);

//    @Override
//    public List<Product> retrieveProductsFromWebService() {
//
//        Call<List<Product>> call = webService.retrieveProducts();
//
//        call.enqueue(new Callback<List<Product>>() {
//
//            @Override
//            public void onResponse(Call<List<Product>> call, @NonNull Response<List<Product>> response) {
//
//                if (response.isSuccessful()) {
//                    data = response.body();
//
//                    if (data != null) {
//                        for (Product product :
//                                data) {
//                            Log.i("logLogin", "retrieveProducts sucess at onResponse:" + product.getName());
//                        }
//                    } else {
//                        Log.i("logLogin", "retrieve products: failed at onResponse == null ");
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Product>> call, Throwable t) {
//                Log.i("logLogin", "onFailure: onFailure "+t.getMessage());
//            }
//        });
//
//        Log.i("logLogin", "retrieveProductsFromWebService: data going to viewmodel " + data);
//        return data;
//    }

    @Override
    public Call<List<Product>> retrieveProductsFromWebService() {
        return webService.retrieveProducts();
    }

}
package com.example.cantinappmobile.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.model.ProductResponse;
import com.example.cantinappmobile.repository.RepositoryImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsFragmentViewModel extends ViewModel {

    private RepositoryImpl repository;
    private MutableLiveData<List<Product>> _productResponseLiveData = new MutableLiveData<>();
    public LiveData<List<Product>> productResponseLiveData = _productResponseLiveData;
    public MutableLiveData<Connection> connectionLiveData = new MutableLiveData<>();


    public ProductsFragmentViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }

    public void retrieveProductsFromRepository() {

        Call<List<Product>> call = repository.retrieveProductsFromWebService();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful()) {

                    List<Product> data = response.body();

                    if (data != null) {
                        for (Product product :
                                data) {
                            Log.i("logLogin", "retrieveProducts sucess at onResponse:" + product.getName());
                        }
                        connectionLiveData.setValue(Connection.Successfull);
                        _productResponseLiveData.setValue(data);

                    } else {
                        Log.i("logLogin", "retrieve products: failed at onResponse == null ");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.i("logLogin", "onFailure: onFailure "+t.getMessage());
                connectionLiveData.setValue(Connection.Failed);
            }
        });

//        if (data != null) {
//            connectionLiveData.setValue(Connection.Successfull);
//            _productResponseLiveData.setValue(data);
//            Log.i("logLogin", "retrieveProductsFromRepository: sucessfull viewmodel "+data);
//        } else {
//            connectionLiveData.setValue(Connection.Failed);
//            Log.i("logLogin", "retrieveProductsFromRepository: failed viewmodel");
//        }
    }
}


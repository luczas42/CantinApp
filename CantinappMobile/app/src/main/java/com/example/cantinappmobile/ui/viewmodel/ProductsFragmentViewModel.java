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

        List<Product> data = repository.retrieveProductsFromWebService();

        if (data != null) {
            connectionLiveData.setValue(Connection.Successfull);
            _productResponseLiveData.setValue(data);
            Log.i("logLogin", "retrieveProductsFromRepository: sucessfull viewmodel "+data);
        } else {
            connectionLiveData.setValue(Connection.Failed);
            Log.i("logLogin", "retrieveProductsFromRepository: failed viewmodel");
        }
    }
}


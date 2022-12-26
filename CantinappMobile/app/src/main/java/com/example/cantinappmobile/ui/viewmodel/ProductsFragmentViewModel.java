package com.example.cantinappmobile.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cantinappmobile.model.ProductResponse;
import com.example.cantinappmobile.repository.RepositoryImpl;
import com.example.cantinappmobile.retrofit.ClientRetrofit;
import com.example.cantinappmobile.retrofit.WebService;

public class ProductsFragmentViewModel extends ViewModel {

    private RepositoryImpl repository;
    private MutableLiveData<ProductResponse> _productResponseLiveData;
    public LiveData<ProductResponse> productResponseLiveData = _productResponseLiveData;

    public ProductsFragmentViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }

    public ProductResponse retrieveProductsToScreen(){
        //calls RepositoryImpl function
        if(repository.retrieveProducts()!=null){
            return repository.retrieveProducts();
        }else{
            return null;
        }
    }

}

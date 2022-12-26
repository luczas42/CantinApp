package com.example.cantinappmobile.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cantinappmobile.model.ProductResponse;
import com.example.cantinappmobile.repository.RepositoryImpl;

public class ProductsFragmentViewModel extends ViewModel {

    private RepositoryImpl repository;
    private MutableLiveData<ProductResponse> _productResponseLiveData;
    public LiveData<ProductResponse> productResponseLiveData = _productResponseLiveData;

    public ProductsFragmentViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }

    public ProductResponse retrieveProductsFromRepository(){
        //calls RepositoryImpl function
        ProductResponse response = repository.retrieveProductsFromWebService();
        if(response!=null){
            Log.i("logLogin", "retrieveProductsToScreen: success");
            return response;
        }else{
            Log.i("logLogin", "retrieveProductsToScreen: failed");
            return null;
        }
    }

}

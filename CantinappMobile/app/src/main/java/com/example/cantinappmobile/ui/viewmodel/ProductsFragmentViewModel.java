package com.example.cantinappmobile.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.cantinappmobile.model.ProductResponse;
import com.example.cantinappmobile.repository.RepositoryImpl;
import com.example.cantinappmobile.retrofit.ClientRetrofit;
import com.example.cantinappmobile.retrofit.WebService;

public class ProductsFragmentViewModel extends ViewModel {

    private RepositoryImpl repository;

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

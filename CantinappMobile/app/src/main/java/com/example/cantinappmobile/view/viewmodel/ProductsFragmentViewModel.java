package com.example.cantinappmobile.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.repository.RepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class ProductsFragmentViewModel extends ViewModel {
    private final SavedStateHandle state;
    private RepositoryImpl repository = new RepositoryImpl();
    private MutableLiveData<List<Product>> _productResponseLiveData = new MutableLiveData<>();
    public LiveData<List<Product>> productResponseLiveData = _productResponseLiveData;
    public MutableLiveData<Connection> connectionLiveData = new MutableLiveData<>();
    public MutableLiveData<String> productSearchQuery = new MutableLiveData<>();

    @Inject
    public ProductsFragmentViewModel(SavedStateHandle state) {
        this.state = state;
    }

    public void retrieveProductsFromRepository() {

        Call<List<Product>> productCall = repository.retrieveProductsFromWebService();

        productCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful()) {
                    List<Product> data = response.body();

                    if (data != null) {
                        connectionLiveData.setValue(Connection.Successfull);
                        _productResponseLiveData.setValue(data);
                    } else {
                        //dá pra melhorar usando outro tipo de erro, pra saber se é erro de conexao ou se veio nulo, mas nao é importante agora
                        connectionLiveData.setValue(Connection.Failed);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                connectionLiveData.setValue(Connection.Failed);
            }
        });
    }
}


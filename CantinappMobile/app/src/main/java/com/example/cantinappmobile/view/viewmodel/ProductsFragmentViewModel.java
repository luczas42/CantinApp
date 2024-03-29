package com.example.cantinappmobile.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class ProductsFragmentViewModel extends ViewModel {
    private final SavedStateHandle state;
    private Repository repository = new Repository();
    private MutableLiveData<List<Product>> _productSaltyResponse = new MutableLiveData<>();
    public LiveData<List<Product>> productSaltyResponse = _productSaltyResponse;

    private MutableLiveData<List<Product>> _productSweetResponse = new MutableLiveData<>();
    public LiveData<List<Product>> productSweetResponse = _productSweetResponse;

    private MutableLiveData<List<Product>> _productHomemadeResponse = new MutableLiveData<>();
    public LiveData<List<Product>> productHomemadeResponse = _productHomemadeResponse;
    public MutableLiveData<Connection> connectionLiveData = new MutableLiveData<>();
    public MutableLiveData<String> productSearchQuery = new MutableLiveData<>();

    @Inject
    public ProductsFragmentViewModel(SavedStateHandle state) {
        this.state = state;
    }

    public void retrieveProductsFromRepository() {

        Call<List<Product>> productCallBack = repository.retrieveProductsFromWebService();


        productCallBack.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if (response.isSuccessful()) {
                    List<Product> saltyList = new ArrayList<>();
                    List<Product> sweetList = new ArrayList<>();
                    List<Product> homemadeList = new ArrayList<>();

                    if (response.body() != null) {
                        connectionLiveData.setValue(Connection.Successfull);
                        for (Product product :
                                response.body()
                        ) {
                            System.out.println(product.getProductType());
                            switch (product.getProductType()) {
                                case 1:
                                    System.out.println("entrou sal");
                                    saltyList.add(product);
                                    break;
                                case 2:
                                    System.out.println("entrou sweet");
                                    sweetList.add(product);
                                    break;
                                case 3:
                                    System.out.println("entrou shee");
                                    homemadeList.add(product);
                                    break;
                            }
                        }
                        _productSaltyResponse.setValue(saltyList);
                        System.out.println(saltyList.size());
                        _productSweetResponse.setValue(sweetList);
                        _productHomemadeResponse.setValue(homemadeList);
                    } else {
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


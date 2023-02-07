package com.example.cantinappmobile.repository;

import com.example.cantinappmobile.model.Employee;
import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.model.Turn;
import com.example.cantinappmobile.model.User;
import com.example.cantinappmobile.retrofit.ClientRetrofit;
import com.example.cantinappmobile.retrofit.WebService;

import java.util.List;

import retrofit2.Call;

public class RepositoryImpl implements Repository {

    private final WebService
            webService = ClientRetrofit.getInstance().create(WebService.class);

    //talvez seja interessante colocar um try catch, mas não sei se é necessário
    @Override
    public Call<List<Product>> retrieveProductsFromWebService() {
        return webService.retrieveProducts();
    }

    public Call<List<User>> userLogin(String username, String password){
        return webService.userLogin(username, password);
    }

    public Call<User> addUser(String username, String name, String password, String email){
        return webService.addUser(username, name, password, 2, email);
    }

    public Call<List<Turn>> getTurn(){
        return webService.getTurns();
    }

    public Call<List<Employee>> getTurnEmployees(int turnId) {
        return webService.getTurnEmployees(turnId);
    }
}
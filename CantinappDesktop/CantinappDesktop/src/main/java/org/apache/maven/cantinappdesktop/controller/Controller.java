package org.apache.maven.cantinappdesktop.controller;

import org.apache.maven.cantinappdesktop.model.*;
import org.apache.maven.cantinappdesktop.retrofit.RetrofitInit;
import retrofit2.Call;
import retrofit2.Callback;

import java.util.List;

public class Controller {
    RetrofitInit retrofitInit = new RetrofitInit();

    ///
    /// USER
    ///
    public void checkLogin(String username, String password, Callback<User> checkLoginCallback){
        Call<User> call = retrofitInit.checkLogin(username, password);
        call.enqueue(checkLoginCallback);
    }

    public void addUser(User user, String password, Callback<ApiResponse> addUserCallback) {
        Call<ApiResponse> call= retrofitInit.addUser(user, password);
        call.enqueue(addUserCallback);
    }

    ///
    /// PRODUCTS
    ///

    public void getProducts(Callback<List<Product>> getProductsCallback) {
        Call<List<Product>> call = retrofitInit.getProducts();
        call.enqueue(getProductsCallback);
    }

    ///
    /// EMPLOYEES
    ///

    public void getEmployees(Callback<List<Employee>> getEmployeesCallback) {
        Call<List<Employee>> call = retrofitInit.getEmployees();
        call.enqueue(getEmployeesCallback);
    }

    ///
    /// SCALES
    ///

    public void getScales(Callback<List<Scale>> getScalesCallback) {
        Call<List<Scale>> call = retrofitInit.getScales();
        call.enqueue(getScalesCallback);
    }
}

package org.apache.maven.cantinappdesktop.viewmodel;

import org.apache.maven.cantinappdesktop.model.*;
import org.apache.maven.cantinappdesktop.repository.Repository;
import retrofit2.Call;
import retrofit2.Callback;

import java.util.List;

public class MainScreenViewModel {
    Repository repository = new Repository();
    public void getProducts(Callback<List<Product>> getProductsCallback) {
        Call<List<Product>> call = repository.getProducts();
        call.enqueue(getProductsCallback);
    }

    public void getEmployees(Callback<List<Employee>> getEmployeesCallback) {
        Call<List<Employee>> call = repository.getEmployees();
        call.enqueue(getEmployeesCallback);
    }

    public void getScales(Callback<List<Scale>> getScalesCallback) {
        Call<List<Scale>> call = repository.getScales();
        call.enqueue(getScalesCallback);
    }
}

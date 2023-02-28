package org.apache.maven.cantinappdesktop.repository;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.apache.maven.cantinappdesktop.model.*;
import org.apache.maven.cantinappdesktop.retrofit.ClientRetrofit;
import org.apache.maven.cantinappdesktop.retrofit.WebService;
import retrofit2.Call;
import retrofit2.Callback;

import java.util.List;

public class Repository {

    private final WebService webService = ClientRetrofit.getInstance().create(WebService.class);

    public Call<List<Product>> getProducts() {
        return this.webService.getProducts();
    }


    public Call<List<Employee>> getEmployees() {
        return this.webService.getEmployees();
    }

    public Call<List<Employee>> getEmployeesWithClass(String _class) {
        return this.webService.getEmployeesWithClass(_class);
    }

    public Call<List<Scale>> getScales() {
        return this.webService.getScales();
    }

    /// INSERTS

    public Call<Product> addProducts(RequestBody name, RequestBody price, RequestBody productType, MultipartBody.Part file) {
        return this.webService.addProduct(name, price, productType, file);
    }

    public Call<Product> addProducts(String name, Float price, int productType) {
        return this.webService.addProduct(name, price, productType);
    }

    public Call<ApiResponse> addUser(User user, String password) {
        return this.webService.addUser(user.getUsername(), user.getName(), password, user.getIsUser(), user.getEmail());
    }

    public Call<Employee> addEmployee(String name, String _class) {
        return this.webService.addEmployee(name, _class);
    }

    /// EDITS

    public Call<ApiResponse> editProducts(RequestBody name, RequestBody price, RequestBody productType, RequestBody productId, MultipartBody.Part file) {
        return this.webService.editProduct(name, price, productType, productId, file);
    }

    public Call<ApiResponse> editProducts(RequestBody name, RequestBody price, RequestBody productType, RequestBody productId) {
        return this.webService.editProduct(name, price, productType, productId);
    }

    /// DELETES

    public Call<Void> deleteProduct(int id) {
        return this.webService.deleteProduct(id);
    }

    public Call<Void> deleteEmployee(int id) {
        return this.webService.deleteEmployee(id);
    }
    /// VERIFICATION

    public Call<User> checkLogin(String username, String password) {
        return this.webService.userLogin(username, password);
    }

    public Call<Scale> addScale(String day, String period, String _class, List<Integer> employees) {
        return this.webService.addScale(day, period, _class, employees);
    }

    public Call<Employee> editEmployee(Employee employee) {
        return this.webService.editEmployee(employee.getId(), employee.getName(), employee.getClasS());
    }

    public Call<Void> deleteScale(int turnId) {

        return this.webService.deleteScale(turnId);
    }
}

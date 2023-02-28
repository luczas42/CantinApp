package org.apache.maven.cantinappdesktop.viewmodel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.apache.maven.cantinappdesktop.model.ApiResponse;
import org.apache.maven.cantinappdesktop.model.Product;
import org.apache.maven.cantinappdesktop.repository.Repository;
import retrofit2.Call;
import retrofit2.Callback;

import java.io.File;

public class ProductDetailsViewModel {
    Repository repository = new Repository();

    public void deleteProduct(int id, Callback<Void> deleteProductCallback) {
        Call<Void> call = repository.deleteProduct(id);
        call.enqueue(deleteProductCallback);
    }

    public void editProducts(RequestBody name, RequestBody price, RequestBody productType, RequestBody productId, Callback<ApiResponse> editProductCallback) {
        Call<ApiResponse> call = repository.editProducts(name, price, productType, productId);
        call.enqueue(editProductCallback);
    }

    public void editProducts(RequestBody name, RequestBody price, RequestBody productType, RequestBody productId, MultipartBody.Part file, Callback<ApiResponse> editProductCallback) {
        Call<ApiResponse> call = repository.editProducts(name, price, productType, productId, file);
        call.enqueue(editProductCallback);
    }

    public void addProducts(RequestBody name, RequestBody price, RequestBody productType, MultipartBody.Part file, Callback<Product> addProductCallback) {
        Call<Product> call = repository.addProducts(name, price, productType, file);
        call.enqueue(addProductCallback);
    }

    public void addProducts(String name, Float price, int productType, Callback<Product> addProductCallback) {
        Call<Product> call = repository.addProducts(name, price, productType);
        call.enqueue(addProductCallback);
    }
}

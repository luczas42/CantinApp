package com.example.cantinappmobile.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cantinappmobile.databinding.FragmentProductsBinding;
import com.example.cantinappmobile.model.ProductResponse;
import com.example.cantinappmobile.repository.RepositoryImpl;
import com.example.cantinappmobile.ui.adapter.ProductListAdapter;
import com.example.cantinappmobile.ui.viewmodel.ProductsFragmentViewModel;

public class ProductsFragment extends Fragment {

    private FragmentProductsBinding binding;
    private ProductListAdapter productAdapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentProductsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productAdapter  = new ProductListAdapter();
        binding.recyclerProducts.setAdapter(productAdapter);
        binding.recyclerProducts.setLayoutManager(new LinearLayoutManager(requireContext()));

        ProductsFragmentViewModel viewModel = new ProductsFragmentViewModel(new RepositoryImpl());
        ProductResponse response = viewModel.retrieveProductsFromRepository();

        if(response==null){
            Log.i("logLogin", "onViewCreated: responseIsNull");
        }else{
            Log.i("logLogin", "onViewCreated: responseIsFull");
        }

        productAdapter.append(response.getProductList());
    }

    private void requestWebService() {

        String urlWebService = "http://54.94.3.48/testephp/getProducts.php";
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        StringRequest stringRequest;

        stringRequest = new StringRequest(Request.Method.POST, urlWebService, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //o que acontece quando a resposta está ok
                Log.i("logLogin", "onResponse: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // o que acontece quando a resposta da erro
                Log.i("logLogin", "onErrorResponse: " + error.getMessage());
            }
        });
        requestQueue.add(stringRequest);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
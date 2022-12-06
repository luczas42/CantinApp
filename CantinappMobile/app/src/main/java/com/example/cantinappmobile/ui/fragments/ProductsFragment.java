package com.example.cantinappmobile.ui.fragments;

import static androidx.fragment.app.FragmentManager.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cantinappmobile.databinding.FragmentProductsBinding;

import java.util.HashMap;
import java.util.Map;

public class ProductsFragment extends Fragment {

    private FragmentProductsBinding binding;

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

        requestWebService();

    }

    private void requestWebService() {

        String urlWebService = "http://127.0.0.1/testephp/getProducts.php";
        StringRequest stringRequest;
        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());

        stringRequest = new StringRequest(Request.Method.POST, urlWebService, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("logLogin", "onResponse: teste funcionaou");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("logLogin", "onErrorResponse: teste nao funcionou");
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("product_name", "kevytos shrek");
                Toast.makeText(requireActivity(), "funcionou a api!", Toast.LENGTH_SHORT).show();
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
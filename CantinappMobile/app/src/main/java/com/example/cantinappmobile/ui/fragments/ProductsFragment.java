package com.example.cantinappmobile.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cantinappmobile.databinding.FragmentProductsBinding;

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

        boolean requestSuccesfull = false;

        if(requestSuccesfull){

            Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
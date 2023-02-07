package com.example.cantinappmobile.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cantinappmobile.databinding.FragmentScalesBinding;
import com.example.cantinappmobile.ui.viewmodel.ScalesViewModel;

public class ScalesFragment extends Fragment {
    ScalesViewModel scalesViewModel = new ScalesViewModel();

    private FragmentScalesBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentScalesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scalesViewModel.openFilters.observe(getViewLifecycleOwner(), open ->{
            if (open){
                binding.classFilter.filtersLayout.setVisibility(View.VISIBLE);
            }else{
                binding.classFilter.filtersLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
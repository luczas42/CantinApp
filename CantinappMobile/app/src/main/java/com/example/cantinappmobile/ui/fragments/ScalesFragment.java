package com.example.cantinappmobile.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cantinappmobile.databinding.FragmentScalesBinding;
import com.example.cantinappmobile.ui.viewmodel.ScalesViewModel;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ScalesFragment extends Fragment {

    private FragmentScalesBinding binding;
    private ScalesViewModel viewModel;

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

        viewModel = new ViewModelProvider(requireActivity()).get(ScalesViewModel.class);
        viewModel.getDisplayValue().observe(getViewLifecycleOwner(),display -> {
            if (display){
                binding.classFilter.filtersLayout.setVisibility(View.VISIBLE);
            }else{
                binding.classFilter.filtersLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
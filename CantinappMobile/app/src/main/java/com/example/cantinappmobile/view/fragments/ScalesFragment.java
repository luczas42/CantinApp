package com.example.cantinappmobile.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cantinappmobile.databinding.FragmentScalesBinding;
import com.example.cantinappmobile.model.Turn;
import com.example.cantinappmobile.view.adapter.ScaleListAdapter;
import com.example.cantinappmobile.view.viewmodel.ScalesViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ScalesFragment extends Fragment {
    ScaleListAdapter scaleAdapter = new ScaleListAdapter();

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
        viewModel.getTurns();
        viewModel.turnLiveData.observe(getViewLifecycleOwner(), this::createAdapter);
        viewModel.getDisplayValue().observe(getViewLifecycleOwner(),display -> {
            if (display){
                binding.classFilter.filtersLayout.setVisibility(View.VISIBLE);
            }else{
                binding.classFilter.filtersLayout.setVisibility(View.GONE);
            }
        });
    }

    private void createAdapter(List<Turn> turns){
        binding.recyclerDays.setAdapter(scaleAdapter);
        binding.recyclerDays.setLayoutManager(new LinearLayoutManager(requireContext()));
        scaleAdapter.append(turns);
        scaleAdapter.setOnClickListener((position, turn) -> {

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
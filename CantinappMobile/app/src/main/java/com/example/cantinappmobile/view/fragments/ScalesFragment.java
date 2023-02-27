package com.example.cantinappmobile.view.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.FragmentScalesBinding;
import com.example.cantinappmobile.model.Employee;
import com.example.cantinappmobile.model.Scale;
import com.example.cantinappmobile.view.adapter.EmployeeAdapter;
import com.example.cantinappmobile.view.adapter.ScaleListAdapter;
import com.example.cantinappmobile.view.viewmodel.Connection;
import com.example.cantinappmobile.view.viewmodel.ScalesViewModel;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ScalesFragment extends Fragment {
    ScaleListAdapter scaleAdapter = new ScaleListAdapter();

    private FragmentScalesBinding binding;
    SwitchCompat inf4amFilter;
    SwitchCompat inf4atFilter;
    SwitchCompat refriFilter;
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

         inf4amFilter = binding.classFilter.filterSwitchInf4am;
         inf4atFilter = binding.classFilter.filterSwitchInf4at;
         refriFilter = binding.classFilter.filterSwitchRefri4am;

        viewModel = new ViewModelProvider(requireActivity()).get(ScalesViewModel.class);
        viewModel.getScales();
        observeConnection();

        viewModel.getDisplayValue().observe(getViewLifecycleOwner(), display -> {
            if (display) {
                binding.classFilter.filtersLayout.setVisibility(View.VISIBLE);
            } else {
                binding.classFilter.filtersLayout.setVisibility(View.GONE);
            }
        });
    }

    private void observeScale() {
        viewModel.scaleLiveData.observe(getViewLifecycleOwner(), this::createAdapter);
    }

    private void observeEmployees(List<Scale> scales) {
        viewModel.employeeConnectionLiveData.observe(getViewLifecycleOwner(), connection -> {
            if (connection == Connection.Successfull) {
                observeSearch(scales);
            }
        });
    }

    private void observeConnection() {
        viewModel.connectionLiveData.observe(getViewLifecycleOwner(), connection -> {
                    if (connection == Connection.Successfull) {
                        observeScale();
                    }
                }
        );
    }

    private void createAdapter(List<Scale> scales) {
        binding.recyclerDays.setAdapter(scaleAdapter);
        binding.recyclerDays.setLayoutManager(new LinearLayoutManager(requireContext()));
        scaleAdapter.append(scales);
        setupFilters(scales);
        scaleAdapter.setOnClickListener((position, scale) -> {
            setupPopup(scale);
        });

        observeEmployees(scales);
    }

    private void observeSearch(List<Scale> scales) {
        viewModel.employeeSearchQuery.observe(getViewLifecycleOwner(), query -> {
            if (!query.equalsIgnoreCase("")) {
                resetFilters();
                scaleAdapter.search(query, scales);
            }else{
                scaleAdapter.append(scales);
            }
        });
    }

    private void resetFilters() {
        inf4amFilter.setChecked(true);
        inf4atFilter.setChecked(true);
        refriFilter.setChecked(true);
    }

    private void setupFilters(List<Scale> scales) {

        binding.classFilter.filterSwitchInf4am.setOnCheckedChangeListener((buttonView, isChecked) -> {
            scaleAdapter.updateItems(scales, inf4amFilter.isChecked(), inf4atFilter.isChecked(), refriFilter.isChecked());
        });

        binding.classFilter.filterSwitchInf4at.setOnCheckedChangeListener((buttonView, isChecked) -> {
            scaleAdapter.updateItems(scales, inf4amFilter.isChecked(), inf4atFilter.isChecked(), refriFilter.isChecked());
        });

        binding.classFilter.filterSwitchRefri4am.setOnCheckedChangeListener((buttonView, isChecked) -> {
            scaleAdapter.updateItems(scales, inf4amFilter.isChecked(), inf4atFilter.isChecked(), refriFilter.isChecked());
        });
    }

    private void setupPopup(Scale scale) {
        Dialog myDialog = new Dialog(requireActivity());
        myDialog.setContentView(R.layout.scale_detail_popup);

        Button closeButton = myDialog.findViewById(R.id.bt_popup_close);
        TextView popupName = myDialog.findViewById(R.id.tv_popup_name);
        TextView scaleDate = myDialog.findViewById(R.id.tv_day_info);
        TextView employeeClass = myDialog.findViewById(R.id.tv_employee_class_info);
        EmployeeAdapter employeeAdapter = new EmployeeAdapter();
        employeeAdapter.append(scale.getEmployeeList());
        RecyclerView employeeRecycler = myDialog.findViewById(R.id.recycler_day_employees);
        employeeRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        employeeRecycler.setAdapter(employeeAdapter);

        popupName.setText("Responsáveis");
        scaleDate.setText(scale.getDay().concat(" - ").concat(scale.getPeriod()));
        employeeClass.setText(scale.get_class());

        setPopupClick(myDialog, closeButton);
        myDialog.show();
    }

    private void setPopupClick(Dialog myDialog, Button closeButton) {
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
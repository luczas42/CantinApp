package com.example.cantinappmobile.view.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.FragmentScalesBinding;
import com.example.cantinappmobile.model.Turn;
import com.example.cantinappmobile.view.adapter.EmployeeAdapter;
import com.example.cantinappmobile.view.adapter.ScaleListAdapter;
import com.example.cantinappmobile.view.viewmodel.Connection;
import com.example.cantinappmobile.view.viewmodel.ProductsFragmentViewModel;
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
        viewModel.turnLiveData.observe(getViewLifecycleOwner(), this::createAdapter);
    }

    private void observeEmployees(List<Turn> turns){
        viewModel.employeeConnectionLiveData.observe(getViewLifecycleOwner(), connection -> {
            if (connection == Connection.Successfull){
                observeSearch(turns);
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

    private void createAdapter(List<Turn> turns) {
        binding.recyclerDays.setAdapter(scaleAdapter);
        binding.recyclerDays.setLayoutManager(new LinearLayoutManager(requireContext()));
        scaleAdapter.append(turns);

        scaleAdapter.setOnClickListener((position, turn) -> {
            setupPopup(turn);
        });

        observeEmployees(turns);
    }

    private void observeSearch(List<Turn> turns) {
        viewModel.employeeSearchQuery.observe(getViewLifecycleOwner(), query -> {
            if (!query.equalsIgnoreCase("")){
                scaleAdapter.search(query, turns);
            }
        });
    }

    private void setupPopup(Turn turn) {
        Dialog myDialog = new Dialog(requireActivity());
        myDialog.setContentView(R.layout.scale_detail_popup);

        Button closeButton = myDialog.findViewById(R.id.bt_popup_close);
        TextView popupName = myDialog.findViewById(R.id.tv_popup_name);
        TextView scaleDate = myDialog.findViewById(R.id.tv_day_info);
        TextView employeeClass = myDialog.findViewById(R.id.tv_employee_class_info);
        EmployeeAdapter employeeAdapter = new EmployeeAdapter();
        employeeAdapter.append(turn.getEmployeeList());
        RecyclerView employeeRecycler = myDialog.findViewById(R.id.recycler_day_employees);
        employeeRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        employeeRecycler.setAdapter(employeeAdapter);

        popupName.setText("Responsáveis");
        scaleDate.setText(turn.getFormatedDate().concat(" - ").concat(turn.getLiteralPeriod()));
        employeeClass.setText(turn.getEmployeeClass());

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
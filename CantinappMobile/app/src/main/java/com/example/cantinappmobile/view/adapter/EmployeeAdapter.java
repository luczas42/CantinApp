package com.example.cantinappmobile.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cantinappmobile.databinding.EmployeesListitemBinding;
import com.example.cantinappmobile.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private List<Employee> employeeList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeAdapter.ViewHolder(EmployeesListitemBinding
                .inflate(LayoutInflater
                                .from(parent.getContext())
                        , parent
                        , false)
        );
    }

    public void append(List<Employee> newList) {
        employeeList.clear();
        employeeList.addAll(newList);
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee employee = employeeList.get(position);
        holder.bind(employee);
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final EmployeesListitemBinding binding;

        public ViewHolder(@NonNull EmployeesListitemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Employee employee) {
            binding.recyclerEmployeeName.setText(employee.getName());
        }
    }
}

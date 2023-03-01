package com.example.cantinappmobile.view.adapter;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cantinappmobile.databinding.DaysListitemBinding;
import com.example.cantinappmobile.model.Employee;
import com.example.cantinappmobile.model.Scale;

import java.util.ArrayList;
import java.util.List;

public class ScaleListAdapter extends RecyclerView.Adapter<ScaleListAdapter.ViewHolder> {

    private List<Scale> scaleList = new ArrayList<>();
    private ScaleOnItemClick onItemClick;

    public void setOnClickListener(ScaleOnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DaysListitemBinding
                .inflate(LayoutInflater
                                .from(parent.getContext())
                        , parent
                        , false)
                , onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Scale scale = scaleList.get(position);
        holder.bind(scale);
    }

    @Override
    public int getItemCount() {
        return scaleList.size();
    }

    public void append(List<Scale> newList) {
        this.scaleList.clear();
        this.scaleList.addAll(newList);
        notifyDataSetChanged();
    }

    public void search(String query, List<Scale> sortedList) {
        List<Scale> filteredList = new ArrayList<>();
        if (!query.equalsIgnoreCase("")){
            for (Scale scale : sortedList
            ) {
                if (scale.getEmployeeList() != null) {
                    for (Employee employee :
                            scale.getEmployeeList()) {
                        if (employee.getName().toLowerCase().contains(query.toLowerCase())) {
                            filteredList.add(scale);
                            break;
                        }
                    }
                }
            }
        }
        setFilteredList(filteredList);
    }
    public void updateItems(List<Scale> allScales, boolean showInf4am, boolean showInf4at, boolean showRefri) {
        List<Scale> switchFilteredList = new ArrayList<>();
        for (Scale scale : allScales) {
            if (showInf4am && scale.get_class().equalsIgnoreCase("inf4am")) {
                switchFilteredList.add(scale);
            } else if (showInf4at && scale.get_class().equalsIgnoreCase("inf4at")) {
                switchFilteredList.add(scale);
            } else if (showRefri && scale.get_class().equalsIgnoreCase("refri4am")) {
                switchFilteredList.add(scale);
            }
        }
        this.scaleList = switchFilteredList;
        notifyDataSetChanged();
    }

    private void setFilteredList(List<Scale> filteredList) {
        this.scaleList = filteredList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final DaysListitemBinding binding;

        public ViewHolder(@NonNull DaysListitemBinding binding, ScaleOnItemClick clickListener) {
            super(binding.getRoot());
            this.binding = binding;
            View itemView = binding.getRoot();
            itemView.setOnClickListener(v -> clickListener.onItemClick(getBindingAdapterPosition(), scaleList.get(getBindingAdapterPosition())));
        }

        public void bind(Scale scale) {
            binding.recyclerDaysDayDate.setText(scale.getDay().concat(" - ").concat(scale.getPeriod()));
            binding.classTextView.setText(scale.get_class().toUpperCase());
            setCardViewColor(scale);
        }

        private void setCardViewColor(Scale scale) {
            if (scale.get_class().equalsIgnoreCase("inf4am")) {
                binding.recyclerDaysCardviewClass.setCardBackgroundColor(Color.parseColor("#1849C7"));
            } else if (scale.get_class().equalsIgnoreCase("inf4at")) {
                binding.recyclerDaysCardviewClass.setCardBackgroundColor(Color.parseColor("#000103"));
            } else if (scale.get_class().equalsIgnoreCase("refri4am")) {
                binding.recyclerDaysCardviewClass.setCardBackgroundColor(Color.parseColor("#921835"));
            }
        }
    }

}

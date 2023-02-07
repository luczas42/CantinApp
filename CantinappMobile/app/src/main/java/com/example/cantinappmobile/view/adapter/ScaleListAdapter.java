package com.example.cantinappmobile.view.adapter;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cantinappmobile.databinding.DaysListitemBinding;
import com.example.cantinappmobile.model.Turn;

import java.util.ArrayList;
import java.util.List;

public class ScaleListAdapter extends RecyclerView.Adapter<ScaleListAdapter.ViewHolder> {

    private ArrayList<Turn> turnList = new ArrayList<>();
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
        Turn turn = turnList.get(position);
        holder.bind(turn);
    }

    @Override
    public int getItemCount() {
        return turnList.size();
    }

    public void append(List<Turn> newList) {
        this.turnList.clear();
        this.turnList.addAll(newList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final DaysListitemBinding binding;

        public ViewHolder(@NonNull DaysListitemBinding binding, ScaleOnItemClick clickListener) {
            super(binding.getRoot());
            this.binding = binding;
            View itemView = binding.getRoot();
            itemView.setOnClickListener(v -> clickListener.onItemClick(getBindingAdapterPosition(), turnList.get(getBindingAdapterPosition())));
        }

        public void bind(Turn turn) {
            binding.recyclerDaysDayDate.setText(turn.getDay().concat(" - ").concat(turn.getLiteralPeriod()));
            binding.classTextView.setText(turn.getEmployeeClass().toUpperCase());
            if (turn.getEmployeeClass().equalsIgnoreCase("inf4am")) {
                binding.recyclerDaysCardviewClass.setCardBackgroundColor(Color.parseColor("#1849C7"));
            } else if (turn.getEmployeeClass().equalsIgnoreCase("inf4at")) {
                binding.recyclerDaysCardviewClass.setCardBackgroundColor(Color.parseColor("#000103"));
            } else if (turn.getEmployeeClass().equalsIgnoreCase("refri4at")) {
                binding.recyclerDaysCardviewClass.setCardBackgroundColor(Color.parseColor("#921835"));
            }
        }
    }

}

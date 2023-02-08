package com.example.cantinappmobile.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cantinappmobile.databinding.ProductListitemBinding;
import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.resources.MoneyFormatter;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private List<Product> productList = new ArrayList<>();
    private AdapterOnItemClick onItemClick;

    public void setFilteredList(List<Product> filteredList) {
        this.productList = filteredList;
        notifyDataSetChanged();
    }

    public void setOnClickListener(AdapterOnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ProductListitemBinding
                .inflate(LayoutInflater
                                .from(parent.getContext())
                        , parent
                        , false)
                , onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void append(List<Product> newList) {
        this.productList.clear();
        this.productList.addAll(newList);
        notifyDataSetChanged();
    }

    public void search(String query, List<Product> sortedList) {
        List<Product> filteredList = new ArrayList<>();

        for (Product product : sortedList
        ) {
            if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(product);
            }
        }
        setFilteredList(filteredList);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ProductListitemBinding binding;

        public ViewHolder(@NonNull ProductListitemBinding binding, AdapterOnItemClick clickListener) {
            super(binding.getRoot());
            this.binding = binding;
            View itemView = binding.getRoot();
            itemView.setOnClickListener(v -> clickListener.onItemClick(getBindingAdapterPosition(), productList.get(getBindingAdapterPosition())));
        }

        public void bind(Product product) {
            binding.recyclerProductName.setText(product.getName());
            binding.recyclerProductPrice.setText(MoneyFormatter.moneyFormat(product.getPrice()));
        }
    }

}

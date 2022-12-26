package com.example.cantinappmobile.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cantinappmobile.databinding.ProductListitemBinding;
import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.model.ProductResponse;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private ArrayList<Product> productList = new ArrayList<>();


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ProductListitemBinding
                .inflate(LayoutInflater
                        .from(parent.getContext())
                        , parent
                        , false)
        );
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

    public void append(ArrayList<Product> newList) {
        this.productList.clear();
        this.productList.addAll(newList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ProductListitemBinding binding;

        public ViewHolder(@NonNull ProductListitemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Product product) {
            binding.recyclerProductName.setText(product.getName());
            binding.recyclerProductPrice.setText(product.getPrice().toString());
        }
    }

}

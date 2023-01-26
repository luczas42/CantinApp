package com.example.cantinappmobile.ui.adapter;

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

    private ArrayList<Product> productList = new ArrayList<>();
    private AdapterOnItemClick onItemClick;

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

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ProductListitemBinding binding;

        public ViewHolder(@NonNull ProductListitemBinding binding, AdapterOnItemClick clickListener) {
            super(binding.getRoot());
            this.binding = binding;
            View itemView = binding.getRoot();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(getBindingAdapterPosition(), productList.get(getBindingAdapterPosition()));
                }
            });
        }

        public void bind(Product product) {
            binding.recyclerProductName.setText(product.getName());
            binding.recyclerProductPrice.setText(MoneyFormatter.moneyFormat(product.getPrice()));
        }
    }

}

package com.example.cantinappmobile.ui.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.FragmentProductsBinding;
import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.repository.RepositoryImpl;
import com.example.cantinappmobile.resources.MoneyFormatter;
import com.example.cantinappmobile.ui.adapter.ProductListAdapter;
import com.example.cantinappmobile.ui.adapter.AdapterOnItemClick;
import com.example.cantinappmobile.ui.viewmodel.Connection;
import com.example.cantinappmobile.ui.viewmodel.ProductsFragmentViewModel;

import java.util.List;

public class ProductsFragment extends Fragment {

    private FragmentProductsBinding binding;
    private ProductListAdapter productAdapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentProductsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProductsFragmentViewModel viewModel = new ProductsFragmentViewModel(new RepositoryImpl());
        viewModel.retrieveProductsFromRepository();
        observeConnection(viewModel);
    }


    private void observeConnection(ProductsFragmentViewModel viewModel) {
        viewModel.connectionLiveData.observe(getViewLifecycleOwner(), connection -> {
                    if (connection == Connection.Successfull) {
                        observeProducts(viewModel);
                    } else {
                        apiError();
                    }
                }
        );
    }

    private void observeProducts(ProductsFragmentViewModel viewModel) {
        binding.tvError.setVisibility(View.GONE);
        viewModel.productResponseLiveData.observe(getViewLifecycleOwner(), this::createAdapter);
    }

    private void apiError() {
        binding.tvError.setVisibility(View.VISIBLE);
    }

    private void createAdapter(List<Product> productList) {
        productAdapter = new ProductListAdapter();
        binding.recyclerProducts.setAdapter(productAdapter);
        binding.recyclerProducts.setLayoutManager(new LinearLayoutManager(requireContext()));
        productAdapter.append(productList);
        productAdapter.setOnClickListener(new AdapterOnItemClick() {
            @Override
            public void onItemClick(int position, Product product) {
                setupPopup(product);
            }
        });
    }

    private void setupPopup(Product product) {
        Dialog myDialog = new Dialog(requireActivity());
        myDialog.setContentView(R.layout.product_description_popup);

        Button closeButton = myDialog.findViewById(R.id.bt_popup_close);
        TextView productName = myDialog.findViewById(R.id.tv_popup_name);
        TextView productPrice = myDialog.findViewById(R.id.tv_popup_price);

        productName.setText(product.getName());
        productPrice.setText(MoneyFormatter.moneyFormat(product.getPrice()));

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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
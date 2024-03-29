package com.example.cantinappmobile.view.fragments;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.FragmentProductsBinding;
import com.example.cantinappmobile.model.Product;
import com.example.cantinappmobile.resources.MoneyFormatter;
import com.example.cantinappmobile.view.adapter.ProductListAdapter;
import com.example.cantinappmobile.view.viewmodel.Connection;
import com.example.cantinappmobile.view.viewmodel.ProductsFragmentViewModel;

import java.util.List;

public class ProductsFragment extends Fragment {

    private FragmentProductsBinding binding;
    ProductListAdapter saltyProductAdapter = new ProductListAdapter();
    ProductListAdapter sweetProductAdapter = new ProductListAdapter();
    ProductListAdapter homemadeProductAdapter = new ProductListAdapter();
    ProductsFragmentViewModel viewModel;

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
        viewModel = new ViewModelProvider(requireActivity()).get(ProductsFragmentViewModel.class);
        viewModel.retrieveProductsFromRepository();
        observeConnection(viewModel);
    }

    @Override
    public void onResume() {
        super.onResume();
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
        viewModel.productSaltyResponse.observe(getViewLifecycleOwner(), this::createSaltyAdapter);
        viewModel.productSweetResponse.observe(getViewLifecycleOwner(), this::createSweetAdapter);
        viewModel.productHomemadeResponse.observe(getViewLifecycleOwner(), this::createHomemadeAdapter);
    }

    private void apiError() {
        binding.tvError.setVisibility(View.VISIBLE);
    }

    private void createSaltyAdapter(List<Product>  saltyList) {
        System.out.println( saltyList.size()+ "listaSize");
        binding.recyclerSaltyProducts.setAdapter(saltyProductAdapter);
        binding.recyclerSaltyProducts.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        saltyProductAdapter.append( saltyList);
        saltyProductAdapter.setOnClickListener((position, product) -> setupPopup(product));
        viewModel.productSearchQuery.observe(getViewLifecycleOwner(), query -> {
            saltyProductAdapter.search(query,  saltyList);
        });
    }
   private void createSweetAdapter(List<Product> sweetList) {
        binding.recyclerSweetProducts.setAdapter(sweetProductAdapter);
        binding.recyclerSweetProducts.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
       sweetProductAdapter.append(sweetList);
       sweetProductAdapter.setOnClickListener((position, product) -> setupPopup(product));
        viewModel.productSearchQuery.observe(getViewLifecycleOwner(), query -> {
            sweetProductAdapter.search(query, sweetList);
        });
    }
   private void createHomemadeAdapter(List<Product> homemadeList) {
        binding.recyclerHomemadeProducts.setAdapter(homemadeProductAdapter);
        binding.recyclerHomemadeProducts.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
       homemadeProductAdapter.append(homemadeList);
       homemadeProductAdapter.setOnClickListener((position, product) -> setupPopup(product));
        viewModel.productSearchQuery.observe(getViewLifecycleOwner(), query -> {
            homemadeProductAdapter.search(query, homemadeList);
        });
    }

    private void setupPopup(Product product) {
        Dialog myDialog = new Dialog(requireActivity());
        myDialog.setContentView(R.layout.product_description_popup);

        Button closeButton = myDialog.findViewById(R.id.bt_popup_close);
        TextView productName = myDialog.findViewById(R.id.tv_popup_name);
        TextView productPrice = myDialog.findViewById(R.id.tv_popup_price);
        ImageView imageView = myDialog.findViewById(R.id.iv_popup_image);

        productName.setText(product.getName());
        productPrice.setText(MoneyFormatter.moneyFormat(product.getPrice()));
        if (product.getImage()!=null){
            byte[] imageData = Base64.decode(product.getImage(), Base64.DEFAULT);
            Bitmap bmp= BitmapFactory.decodeByteArray(imageData,0,imageData.length);
            imageView.setImageBitmap(bmp);
        }

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
package com.example.cantinappmobile.view.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.ActivityListsBinding;
import com.example.cantinappmobile.databinding.ContentListsBinding;
import com.example.cantinappmobile.view.fragments.ProductsFragment;
import com.example.cantinappmobile.view.fragments.ScalesFragment;
import com.example.cantinappmobile.view.viewmodel.ProductsFragmentViewModel;
import com.example.cantinappmobile.view.viewmodel.ScalesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListsActivity extends AppCompatActivity {
    public ScalesViewModel scalesViewModel;
    public ProductsFragmentViewModel productsViewModel;
    private int currentFragment = 1;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scalesViewModel = new ViewModelProvider(this).get(ScalesViewModel.class);
        productsViewModel = new ViewModelProvider(this).get(ProductsFragmentViewModel.class);
        ActivityListsBinding binding = ActivityListsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ContentListsBinding contentListsBinding = binding.contentLists;
        FragmentManager fragmentManager = getSupportFragmentManager();

        setupWorkdaysButton(contentListsBinding, fragmentManager);

        displayFilters(contentListsBinding);

        configureSearchView(contentListsBinding);

        setupBackButton(contentListsBinding, fragmentManager);
    }

    private void configureSearchView(ContentListsBinding contentListsBinding) {
        if (currentFragment == 2) {
            contentListsBinding.searchView.setQueryHint("Pesquisar por aluno");

            contentListsBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        } else if (currentFragment == 1) {
            contentListsBinding.searchView.setQueryHint("Pesquisar por produto");

            contentListsBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    productsViewModel.productSearchQuery.setValue(newText);
                    return false;
                }
            });
        }
    }

    private void displayFilters(ContentListsBinding contentListsBinding) {
        contentListsBinding.buttonFilters.setOnClickListener(v -> {
            scalesViewModel.setDisplayValue();
        });
    }

    private void setupBackButton(ContentListsBinding contentListsBinding, FragmentManager fragmentManager) {
        contentListsBinding.backButton.setOnClickListener(v -> {
            goToProducts(fragmentManager);
            contentListsBinding.backButton.setVisibility(View.GONE);
            contentListsBinding.buttonWorkdays.setVisibility(View.VISIBLE);
            contentListsBinding.buttonFilters.setVisibility(View.GONE);
            configureSearchView(contentListsBinding);
        });
    }

    private void goToProducts(FragmentManager fragmentManager) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_content_lists, new ProductsFragment(), null);
        fragmentTransaction.commit();
        currentFragment = 1;
    }

    private void setupWorkdaysButton(ContentListsBinding contentListsBinding, FragmentManager fragmentManager) {
        contentListsBinding.buttonWorkdays.setOnClickListener(v -> {
            contentListsBinding.buttonWorkdays.setVisibility(View.GONE);
            contentListsBinding.buttonFilters.setVisibility(View.VISIBLE);
            gotToWorkdays(fragmentManager);
            contentListsBinding.backButton.setVisibility(View.VISIBLE);
            configureSearchView(contentListsBinding);
        });
    }

    private void gotToWorkdays(FragmentManager fragmentManager) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_content_lists, new ScalesFragment(), null);
        fragmentTransaction.commit();
        currentFragment = 2;
    }
}
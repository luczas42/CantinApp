package com.example.cantinappmobile.ui.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.ActivityListsBinding;
import com.example.cantinappmobile.databinding.ContentListsBinding;
import com.example.cantinappmobile.ui.fragments.ProductsFragment;
import com.example.cantinappmobile.ui.fragments.ScalesFragment;
import com.example.cantinappmobile.ui.viewmodel.ScalesViewModel;

public class ListsActivity extends AppCompatActivity {
    ScalesViewModel scalesViewModel = new ScalesViewModel();

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scalesViewModel._openFilter.setValue(true);
        ActivityListsBinding binding = ActivityListsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ContentListsBinding contentListsBinding = binding.contentLists;
        FragmentManager fragmentManager = getSupportFragmentManager();


        contentListsBinding.buttonWorkdays.setOnClickListener(v -> {
            contentListsBinding.buttonWorkdays.setVisibility(View.GONE);
            contentListsBinding.buttonFilters.setVisibility(View.VISIBLE);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.nav_host_fragment_content_lists, new ScalesFragment(), null);
            fragmentTransaction.commit();
        });

        contentListsBinding.buttonFilters.setOnClickListener(v -> {

//            contentListsBinding.buttonWorkdays.setVisibility(View.VISIBLE);
//            contentListsBinding.buttonFilters.setVisibility(View.GONE);
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.nav_host_fragment_content_lists, new ProductsFragment(), null);
//            fragmentTransaction.commit();
        });
    }
}
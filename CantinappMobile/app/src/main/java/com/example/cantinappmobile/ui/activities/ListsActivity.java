package com.example.cantinappmobile.ui.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.ActivityListsBinding;
import com.example.cantinappmobile.databinding.ContentListsBinding;
import com.example.cantinappmobile.ui.fragments.ProductsFragment;

public class ListsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.cantinappmobile.databinding.ActivityListsBinding binding = ActivityListsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ContentListsBinding contentListsBinding = binding.contentLists;
//        FragmentManager fragmentManager = getSupportFragmentManager();

        contentListsBinding.buttonWorkdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentListsBinding.buttonWorkdays.setVisibility(View.GONE);
                contentListsBinding.buttonFilters.setVisibility(View.VISIBLE);
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.nav_host_fragment_content_lists, new WorkDaysFragment(), null);
//                fragmentTransaction.commit();
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_ProductsFragment_to_ScalesFragment);

            }
        });

        contentListsBinding.buttonFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentListsBinding.buttonWorkdays.setVisibility(View.VISIBLE);
                contentListsBinding.buttonFilters.setVisibility(View.GONE);
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.nav_host_fragment_content_lists, new ProductsFragment(), null);
//                fragmentTransaction.commit();
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_ScalesFragment_to_ProductsFragment);
            }
        });
    }
}
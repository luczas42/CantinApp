package com.example.cantinappmobile.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.ActivityListsBinding;
import com.example.cantinappmobile.databinding.ContentListsBinding;
import com.example.cantinappmobile.ui.fragments.ProductsFragment;
import com.example.cantinappmobile.ui.fragments.WorkDaysFragment;

public class ListsActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.example.cantinappmobile.databinding.ActivityListsBinding binding = ActivityListsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ContentListsBinding contentListsBinding = binding.contentLists;
        FragmentManager fragmentManager = getSupportFragmentManager();

        contentListsBinding.buttonWorkdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentListsBinding.buttonWorkdays.setVisibility(View.GONE);
                contentListsBinding.buttonFilters.setVisibility(View.VISIBLE);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_lists, new WorkDaysFragment(), null);
                fragmentTransaction.commit();
            }
        });

        contentListsBinding.buttonFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentListsBinding.buttonWorkdays.setVisibility(View.VISIBLE);
                contentListsBinding.buttonFilters.setVisibility(View.GONE);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment_content_lists, new ProductsFragment(), null);
                fragmentTransaction.commit();
            }
        });



//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_lists);
//        NavController navController = navHostFragment.getNavController();



    }
}
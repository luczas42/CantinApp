package com.example.cantinappmobile.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< Updated upstream
import androidx.navigation.Navigation;
=======
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
>>>>>>> Stashed changes

import android.os.Bundle;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.ActivityLoginBinding;
<<<<<<< Updated upstream
=======
import com.example.cantinappmobile.databinding.ContentLoginBinding;
import com.example.cantinappmobile.ui.fragments.LoginFragment;
>>>>>>> Stashed changes

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

<<<<<<< Updated upstream
=======
        FragmentManager fragmentManager = getSupportFragmentManager();
        ContentLoginBinding contentLoginBinding = binding.contentLogin;

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view, new LoginFragment(), null);
        fragmentTransaction.commit();
>>>>>>> Stashed changes

    }
}
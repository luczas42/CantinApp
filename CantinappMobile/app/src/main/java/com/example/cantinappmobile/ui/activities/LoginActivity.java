package com.example.cantinappmobile.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cantinappmobile.databinding.ActivityLoginBinding;
import com.example.cantinappmobile.databinding.ContentLoginBinding;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ContentLoginBinding contentLoginBinding = binding.contentLogin;
    }
}
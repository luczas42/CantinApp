package com.example.cantinappmobile.ui.fragments;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.FragmentSignInBinding;

public class SignInFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentSignInBinding binding = FragmentSignInBinding.inflate(getLayoutInflater());

        binding.btSigninFragmentSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findNavController(view).navigate(R.id.action_SignInFragment_to_LoginFragment);
            }
        });
    }
}
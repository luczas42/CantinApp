package com.example.cantinappmobile.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.FragmentLoginBinding;
import com.example.cantinappmobile.ui.activities.ListsActivity;

public class LoginFragment extends Fragment {

    Button btLogin;
    TextView tvSignin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentLoginBinding binding = FragmentLoginBinding.inflate(getLayoutInflater());

        btLogin = binding.btLoginFragmentLogin;
        tvSignin = binding.tvLoginFragmentSignIn;

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), ListsActivity.class);
                startActivity(intent);
                Toast.makeText(requireActivity(), "asdasdasdasdsa", Toast.LENGTH_SHORT).show();
            }
        });

        tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_LoginFragment_to_SignInFragment);
                Toast.makeText(requireActivity(), "bdbdbdbdbdbd", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
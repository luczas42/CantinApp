package com.example.cantinappmobile.ui.fragments;

import static androidx.navigation.Navigation.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavAction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.FragmentLoginBinding;
import com.example.cantinappmobile.databinding.FragmentSignInBinding;
import com.example.cantinappmobile.repository.RepositoryImpl;
import com.example.cantinappmobile.ui.viewmodel.LoginScreenViewModel;

public class SignInFragment extends Fragment {
    private FragmentSignInBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginScreenViewModel viewModel = new LoginScreenViewModel(new RepositoryImpl());
        binding.cancelButton.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_SignInFragment_to_LoginFragment));

        binding.signinButton.setOnClickListener(v -> {
            String username, name, password, email;
            username = binding.userUsernameEditText.getText().toString();
            name = binding.userNameEditText.getText().toString();
            password = binding.userPasswordEditText.getText().toString();
            email = binding.userEmailEditText.getText().toString();
            viewModel.addUser(username, name, password, email);
            Navigation.findNavController(v).navigate(R.id.action_SignInFragment_to_LoginFragment);
        });

    }
}
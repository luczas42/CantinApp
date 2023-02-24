package com.example.cantinappmobile.view.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.FragmentSignInBinding;
import com.example.cantinappmobile.repository.RepositoryImpl;
import com.example.cantinappmobile.view.viewmodel.LoginScreenViewModel;

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


            if (viewModel.checkEmpty(binding.userNameEditText)) {
                if (viewModel.checkEmpty(binding.userUsernameEditText)) {
                    if (viewModel.checkEmpty(binding.userPasswordEditText)) {
                        if (viewModel.checkEmpty(binding.userEmailEditText)) {
                            if (viewModel.checkEmpty(binding.userConfirmPasswordEditText)) {
                                username = binding.userUsernameEditText.getText().toString();
                                name = binding.userNameEditText.getText().toString();
                                password = binding.userPasswordEditText.getText().toString();
                                email = binding.userEmailEditText.getText().toString();
                                if (binding.userPasswordEditText.getText().toString().equals
                                        (binding.userConfirmPasswordEditText.getText().toString())) {
                                    viewModel.addUser(username, name, password, email);
                                    Dialog myDialog = new Dialog(requireActivity());
                                    myDialog.setContentView(R.layout.confirm_account_created_popup);
                                    Button closeButton = myDialog.findViewById(R.id.bt_popup_dismiss);
                                    myDialog.show();
                                    closeButton.setOnClickListener(v1 -> {
                                        Navigation.findNavController(view).navigate(R.id.action_SignInFragment_to_LoginFragment);
                                        myDialog.dismiss();
                                    });

                                } else {
                                    binding.userConfirmPasswordEditText.requestFocus();
                                    binding.userConfirmPasswordEditText.setError("Senhas não coincidem");
                                }

                            }
                        }
                    }
                }
            }

        });

    }
}
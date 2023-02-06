package com.example.cantinappmobile.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.FragmentLoginBinding;
import com.example.cantinappmobile.repository.RepositoryImpl;
import com.example.cantinappmobile.ui.activities.ListsActivity;
import com.example.cantinappmobile.ui.viewmodel.LoginScreenViewModel;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        NavController navController = Navigation.findNavController(view);
        LoginScreenViewModel viewModel = new LoginScreenViewModel(new RepositoryImpl());


        super.onViewCreated(view, savedInstanceState);

        binding.loginButton.setOnClickListener(v -> {
            if (viewModel.checkEmpty(binding.userLoginEditText)){
                if (viewModel.checkEmpty(binding.userPasswordEditText)){
                    String username = binding.userLoginEditText.getText().toString();
                    String password = binding.userPasswordEditText.getText().toString();
                    viewModel.userLogin(username, password);
                    viewModel.userResponseLiveData.observe(getViewLifecycleOwner(), user -> {
                        if (user!= null){
                            Log.i("login", "onViewCreated: "+ username);
                            Intent intent = new Intent(requireContext(), ListsActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(requireContext(), "Credenciais erradas", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_LoginFragment_to_SignInFragment);
                Toast.makeText(requireActivity(), "bdbdbdbdbdbd", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.cantinappmobile.R;
import com.example.cantinappmobile.databinding.FragmentLoginBinding;
import com.example.cantinappmobile.ui.activities.ListsActivity;

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
        ActivityNavigator activityNavigator = new ActivityNavigator(requireActivity());


        super.onViewCreated(view, savedInstanceState);

        binding.loginButton.setOnClickListener(v -> {
            Log.i("teste", "onViewCreated: test");
//            navController.navigate(R.id.ListsActivity);
            activityNavigator.navigate(activityNavigator.createDestination().setIntent(new Intent(requireContext(), ListsActivity.class)),null,null, null);
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
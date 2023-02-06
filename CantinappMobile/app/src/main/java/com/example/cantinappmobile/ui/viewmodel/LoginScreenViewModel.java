package com.example.cantinappmobile.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cantinappmobile.model.User;
import com.example.cantinappmobile.repository.RepositoryImpl;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreenViewModel extends ViewModel {

    private RepositoryImpl repository;
    private MutableLiveData<User> _userResponseLiveData = new MutableLiveData<>();
    public LiveData<User> userResponseLiveData = _userResponseLiveData;
    public MutableLiveData<Connection> connectionLiveData = new MutableLiveData<>();

    public LoginScreenViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }

    public void userLogin(String username, String password){
        Call<List<User>> userCall = repository.userLogin(username,password);

        userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                assert response.body() != null;
                if (response.body().size()!= 0){
                    _userResponseLiveData.setValue(response.body().get(0));
                    Log.i("loginSuccess", userResponseLiveData.getValue().getUsername());
                    Log.i("loginSuccess", String.valueOf(response.body().size()));
                }else{
                    _userResponseLiveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i("loginFail", "onFailure: "+ t.getMessage());
            }
        });
    }

    public void addUser(String username, String name, String password, String email){
        Call<User> addUserCall = repository.addUser(username,name,password,email);

        addUserCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println(response.body().getUsername());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}


package com.example.cantinappmobile.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cantinappmobile.model.User;
import com.example.cantinappmobile.repository.RepositoryImpl;

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

    public boolean userLogin(String username, String password){
        Call<User> userCall = repository.userLogin(username,password);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                _userResponseLiveData.setValue(response.body());
                connectionLiveData.setValue(Connection.Successfull);
                Log.i("login", "userLogin: ");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("loginfail", "onFailure: "+t.getMessage());
                _userResponseLiveData.setValue(null);
                connectionLiveData.setValue(Connection.Failed);
            }
        });

        if (userResponseLiveData.getValue()!=null){
            return true;
        }else{
            return false;
        }
    }

}


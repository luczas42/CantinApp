package com.example.cantinappmobile.view.viewmodel;

import android.util.Log;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cantinappmobile.model.User;
import com.example.cantinappmobile.model.UserApiReturn;
import com.example.cantinappmobile.repository.RepositoryImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreenViewModel extends ViewModel {

    private RepositoryImpl repository;
    private MutableLiveData<User> _userResponseLiveData = new MutableLiveData<>();
    public LiveData<User> userResponseLiveData = _userResponseLiveData;
    private MutableLiveData<Boolean> _userCreatedLiveData = new MutableLiveData<>();
    public LiveData<Boolean> userCreatedLiveData = _userCreatedLiveData;
    public MutableLiveData<Connection> connectionLiveData = new MutableLiveData<>();

    public LoginScreenViewModel(RepositoryImpl repository) {
        this.repository = repository;
    }


    public boolean checkEmpty(EditText editText){
        if (editText.getText().toString().isEmpty()){
            editText.requestFocus();
            editText.setError("Preencha todos os campos");
            return false;
        }else{
            return true;
        }
    }
    public void userLogin(String username, String password){
        Call<List<User>> userCall = repository.userLogin(username,password);

        userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                assert response.body() != null;
                if (response.body().size()!= 0){
                    _userResponseLiveData.setValue(response.body().get(0));
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
        Call<UserApiReturn> addUserCall = repository.addUser(username,name,password,email);

        addUserCall.enqueue(new Callback<UserApiReturn>() {
            @Override
            public void onResponse(Call<UserApiReturn> call, Response<UserApiReturn> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()){
                        _userCreatedLiveData.setValue(true);
                    } else{
                        _userCreatedLiveData.setValue(false);
                    }
                }

            }

            @Override
            public void onFailure(Call<UserApiReturn> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

}


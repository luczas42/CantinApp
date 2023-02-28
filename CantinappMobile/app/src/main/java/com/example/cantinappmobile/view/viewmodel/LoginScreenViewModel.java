package com.example.cantinappmobile.view.viewmodel;

import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cantinappmobile.model.User;
import com.example.cantinappmobile.model.UserApiReturn;
import com.example.cantinappmobile.repository.Repository;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreenViewModel extends ViewModel {

    private Repository repository;
    private MutableLiveData<Boolean> _userLoginLiveData = new MutableLiveData<>();
    public LiveData<Boolean> userLoginLiveData = _userLoginLiveData;
    private MutableLiveData<Boolean> _userCreatedLiveData = new MutableLiveData<>();
    public LiveData<Boolean> userCreatedLiveData = _userCreatedLiveData;
    public MutableLiveData<Connection> connectionLiveData = new MutableLiveData<>();

    public LoginScreenViewModel(Repository repository) {
        this.repository = repository;
    }


    public boolean checkEmpty(EditText editText) {
        if (editText.getText().toString().isEmpty()) {
            editText.requestFocus();
            editText.setError("Preencha todos os campos");
            return false;
        } else {
            return true;
        }
    }

    public void userLogin(String username, String password){
        Call<List<User>> userCall = repository.userLogin(username, password);
//        Response<List<User>> response = null;
//        try {
//            response = userCall.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        if (response.isSuccessful()){
//            if(response.body().isEmpty()){
//                System.out.println("login false");
//                _userLoginLiveData.setValue(false);
//            }else{
//                System.out.println("login true");
//                _userLoginLiveData.setValue(true);
//            }
//        }else{
//            _userLoginLiveData.setValue(false);
//        }
                userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                assert response.body() != null;
                if (response.body().isEmpty()){
                    _userLoginLiveData.setValue(false);
                }else{
                    _userLoginLiveData.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                System.out.println("login false");
                _userLoginLiveData.setValue(false);
            }
        });
    }

    public void addUser(String username, String name, String password, String email) {
        Call<UserApiReturn> addUserCall = repository.addUser(username, name, password, email);

        addUserCall.enqueue(new Callback<UserApiReturn>() {
            @Override
            public void onResponse(Call<UserApiReturn> call, Response<UserApiReturn> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSuccess()) {
                        _userCreatedLiveData.setValue(true);
                    } else {
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

    public void reset(){
        _userLoginLiveData.setValue(false);
    }

}


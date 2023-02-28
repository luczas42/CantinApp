package org.apache.maven.cantinappdesktop.viewmodel;

import org.apache.maven.cantinappdesktop.model.ApiResponse;
import org.apache.maven.cantinappdesktop.model.User;
import org.apache.maven.cantinappdesktop.repository.Repository;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginScreenViewModel {
    Repository repository = new Repository();
    public void checkLogin(String username, String password, Callback<User> checkLoginCallback){
        Call<User> call = repository.checkLogin(username, password);
        call.enqueue(checkLoginCallback);
    }

    public void addUser(User user, String password, Callback<ApiResponse> addUserCallback) {
        Call<ApiResponse> call = repository.addUser(user, password);
        call.enqueue(addUserCallback);
    }

}

package org.apache.maven.cantinappdesktop.viewmodel;

import org.apache.maven.cantinappdesktop.model.ApiResponse;
import org.apache.maven.cantinappdesktop.model.User;
import org.apache.maven.cantinappdesktop.repository.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class LoginScreenViewModel {
    Repository repository = new Repository();

    public boolean checkLogin(String username, String password) throws IOException {
//            , Callback<List<User>> checkLoginCallback) throws IOException {
        Call<List<User>> call = repository.checkLogin(username, password);
        Response<List<User>> response = call.execute();
        if (response.isSuccessful()){
            if(response.body().isEmpty()){
                System.out.println("login false");
                return false;
            }else{
                System.out.println("login true");
                return true;
            }
        }else{
            return false;
        }

//        if(call.execute().isSuccessful()){
//            System.out.println("login true");
//            return true;
//        }else{
//            System.out.println("login false");
//            return false;
//        }
    }

    public void addUser(User user, String password, Callback<ApiResponse> addUserCallback) {
        Call<ApiResponse> call = repository.addUser(user, password);
        call.enqueue(addUserCallback);
    }

}

package org.apache.maven.cantinappdesktop.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.apache.maven.cantinappdesktop.model.*;
import org.apache.maven.cantinappdesktop.util.FileTypeAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.File;
import java.util.List;

public class ClientRetrofit {
    public static Retrofit getInstance() {

        //perguntar pros professores em aula se isso não é perigoso

        Retrofit retrofit = null;

        Gson gson = new GsonBuilder().registerTypeAdapter(File.class, new FileTypeAdapter()).create();

        retrofit = (new Retrofit.Builder())
                .baseUrl("http://54.207.241.251/CantinappServer/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }
}

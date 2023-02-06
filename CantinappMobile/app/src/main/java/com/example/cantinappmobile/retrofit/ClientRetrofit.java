package com.example.cantinappmobile.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRetrofit {

    public static Retrofit getInstance() {

        //perguntar pros professores em aula se isso não é perigoso

        Retrofit retrofit = null;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit == null) {
            final String urlWebService = "https://cantinapp.000webhostapp.com/cantinapp/";

            retrofit = new Retrofit.Builder()
                    .baseUrl(urlWebService)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit;
        } else {
            return null;
        }
    }

}

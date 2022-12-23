package com.example.cantinappmobile.retrofit;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRetrofit {

    private static WebService retrofit = null;

    public static WebService create() {

        final String urlWebService = "http://54.94.3.48/testephp/";

        retrofit = new Retrofit.Builder()
                .baseUrl(urlWebService)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(WebService.class);

        return retrofit;
    }

}

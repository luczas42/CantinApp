package com.example.cantinappmobile.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientRetrofit {

    private static Retrofit retrofit = null;

    public static Retrofit getInstance() {

        if(retrofit == null){
            final String urlWebService = "http://54.94.3.48/testephp/";

            retrofit = new Retrofit.Builder()
                    .baseUrl(urlWebService)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit;
        }
    }

}

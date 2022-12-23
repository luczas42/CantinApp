package com.example.cantinappmobile;

import android.app.Application;

import com.example.cantinappmobile.retrofit.ClientRetrofit;

public class AppInfo extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ClientRetrofit.create();
    }
}

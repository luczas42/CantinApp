package com.example.cantinappmobile.di;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
}

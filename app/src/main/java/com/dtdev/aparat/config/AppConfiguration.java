package com.dtdev.aparat.config;

import android.app.Application;

import androidx.multidex.MultiDex;

public class AppConfiguration extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(getApplicationContext());

    }
}

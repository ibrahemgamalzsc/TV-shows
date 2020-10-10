package com.example.tvshows;

import android.app.Application;

import com.example.tvshows.di.component.AppComponent;
import com.example.tvshows.di.component.DaggerAppComponent;

public class BaseApplication extends Application {
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.create();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}


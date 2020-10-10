package com.example.tvshows.di.module;

import android.app.Application;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ApplicationModule {

    private final Application application;

    protected ApplicationModule(Application application) {
        this.application = application;
    }

    @Binds
    abstract Application application(Application application);
}

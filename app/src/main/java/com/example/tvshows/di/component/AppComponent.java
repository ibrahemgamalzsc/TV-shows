package com.example.tvshows.di.component;

import com.example.tvshows.di.module.APIModule;
import com.example.tvshows.di.module.ApplicationModule;
import com.example.tvshows.ui.view.MainActivity;
import com.example.tvshows.ui.view.TvShowDetailsActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {APIModule.class, ApplicationModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(TvShowDetailsActivity tvShowDetailsActivity);
}

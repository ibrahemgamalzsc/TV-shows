package com.example.tvshows.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tvshows.di.annotations.ViewModelKey;
import com.example.tvshows.ui.viewmodle.MainViewModel;
import com.example.tvshows.ui.viewmodle.TvShowDetailViewModel;
import com.example.tvshows.ui.viewmodle.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel provideViewModel(MainViewModel mainViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(TvShowDetailViewModel.class)
    abstract ViewModel provideTvShowDetailViewModel(TvShowDetailViewModel tvShowDetailViewModel);

    @Binds
    abstract ViewModelProvider.Factory factory(ViewModelFactory viewModelFactory);
}

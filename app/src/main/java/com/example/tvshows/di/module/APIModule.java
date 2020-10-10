package com.example.tvshows.di.module;

import com.example.tvshows.data.api.APIServiceResponse;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public abstract class APIModule {

    @Provides
    @Singleton
    static Retrofit provideRetrofit(){
        return new Retrofit.Builder().baseUrl("https://www.episodate.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static APIServiceResponse provideApiServiceResponse(Retrofit retrofit){
        return retrofit.create(APIServiceResponse.class);
    }
}

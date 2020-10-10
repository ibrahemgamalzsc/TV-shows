package com.example.tvshows.ui.viewmodle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tvshows.data.pojo.clickedShow.TvShow;
import com.example.tvshows.data.repo.APIServiceRepository;

import javax.inject.Inject;

public class TvShowDetailViewModel extends ViewModel {
    private final APIServiceRepository apiServiceRepository;

    @Inject
    public TvShowDetailViewModel(APIServiceRepository apiServiceRepository) {
        this.apiServiceRepository = apiServiceRepository;
    }

    public LiveData<TvShow> getSpecificShow(String showId){
        return apiServiceRepository.getSpecificShowData(showId);
    }
}

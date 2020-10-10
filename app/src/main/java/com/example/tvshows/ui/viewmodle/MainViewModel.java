package com.example.tvshows.ui.viewmodle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tvshows.data.pojo.mostPopularAndSearch.MostPopularAndSearch;
import com.example.tvshows.data.repo.APIServiceRepository;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {
    private final APIServiceRepository apiServiceRepository;
    private MutableLiveData<Integer> page;
    private MutableLiveData<Integer> searchPage;
    private MutableLiveData<String> searchKeyWord;
    private static final String TAG = "MainViewModel";

    @Inject
    public MainViewModel(APIServiceRepository apiServiceRepository) {
        page = new MutableLiveData<>();
        page.setValue(1);
        searchPage = new MutableLiveData<>();
        searchKeyWord = new MutableLiveData<>();
        searchPage.setValue(1);
        this.apiServiceRepository = apiServiceRepository;
    }

    public LiveData<MostPopularAndSearch> getMostPopular() {
        return apiServiceRepository.getMostPopularData(page.getValue());
    }


    public void onNextClick() {
        page.setValue(page.getValue() + 1);
        getMostPopular();
    }

    public void onPreviousClick() {
        page.setValue(page.getValue() - 1);
        getMostPopular();
    }

    public void onSearchNextClick() {
        searchPage.setValue(searchPage.getValue() + 1);
        searchForShow(searchKeyWord.getValue());
    }

    public void onSearchPreviousClick() {
        searchPage.setValue(searchPage.getValue() - 1);
        searchForShow(searchKeyWord.getValue());
    }

    public LiveData<MostPopularAndSearch> searchForShow(String showName) {
        showName.replace(" ","%20");
        searchKeyWord.setValue(showName);
        return apiServiceRepository.getSearchForSpecificShowData(showName, searchPage.getValue());
    }
}

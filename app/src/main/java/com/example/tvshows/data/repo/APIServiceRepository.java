package com.example.tvshows.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.tvshows.data.api.APIServiceResponse;
import com.example.tvshows.data.pojo.clickedShow.TvShow;
import com.example.tvshows.data.pojo.mostPopularAndSearch.MostPopularAndSearch;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIServiceRepository {
    private final APIServiceResponse apiServiceResponse;
    private MutableLiveData<MostPopularAndSearch> getMostPopular = new MutableLiveData<>();
    private MutableLiveData<TvShow> getSpecificShow = new MutableLiveData<>();
    private MutableLiveData<MostPopularAndSearch> getSearchForSpecificShow = new MutableLiveData<>();
    private static final String TAG = "APIServiceRepository";

    @Inject
    public APIServiceRepository(APIServiceResponse apiServiceResponse) {
        this.apiServiceResponse = apiServiceResponse;
    }

    private void getMostPopular(int page) {
        apiServiceResponse.getMostPopular(page).enqueue(new Callback<MostPopularAndSearch>() {
            @Override
            public void onResponse(Call<MostPopularAndSearch> call, Response<MostPopularAndSearch> response) {
                if (response.isSuccessful()){
                    getMostPopular.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MostPopularAndSearch> call, Throwable t) {

            }
        });
    }

    private void getSpecificShow(String showId) {
        apiServiceResponse.getSpecificShow(showId).enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                if (response.isSuccessful()){
                    getSpecificShow.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {

            }
        });
    }

    private void searchForSpecificShow(String showName,int page_id) {
        apiServiceResponse.searchForSpecificShow(showName,page_id).enqueue(new Callback<MostPopularAndSearch>() {
            @Override
            public void onResponse(Call<MostPopularAndSearch> call, Response<MostPopularAndSearch> response) {
                if (response.isSuccessful()) {
                    getSearchForSpecificShow.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MostPopularAndSearch> call, Throwable t) {
            }
        });
    }

    public MutableLiveData<MostPopularAndSearch> getMostPopularData(int page) {
        getMostPopular(page);
        return getMostPopular;
    }

    public MutableLiveData<TvShow> getSpecificShowData(String showId) {
        getSpecificShow(showId);
        return getSpecificShow;
    }

    public MutableLiveData<MostPopularAndSearch> getSearchForSpecificShowData(String showName, int page_id) {
        searchForSpecificShow(showName,page_id);
        return getSearchForSpecificShow;
    }
}

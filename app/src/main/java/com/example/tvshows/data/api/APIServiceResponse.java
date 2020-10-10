package com.example.tvshows.data.api;

import com.example.tvshows.data.pojo.clickedShow.TvShow;
import com.example.tvshows.data.pojo.mostPopularAndSearch.MostPopularAndSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIServiceResponse {

    @GET("most-popular")
    Call<MostPopularAndSearch> getMostPopular(@Query("page") int page);

    @GET("show-details")
    Call<TvShow> getSpecificShow(@Query("q") String show_id);

    @GET("search")
    Call<MostPopularAndSearch> searchForSpecificShow(@Query("q") String showName, @Query("page") int page_id);
}
package com.example.tvshows.ui.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.denzcoskun.imageslider.models.SlideModel;
import com.example.tvshows.BaseApplication;
import com.example.tvshows.R;
import com.example.tvshows.data.pojo.clickedShow.TvShow;
import com.example.tvshows.databinding.ActivityTvShowDetailsBinding;
import com.example.tvshows.ui.viewmodle.TvShowDetailViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.tvshows.ui.view.MainActivity.TV_SHOW;

public class TvShowDetailsActivity extends AppCompatActivity {

    private String tvShowId;

    @Inject
    ViewModelProvider.Factory factory;
    private TvShowDetailViewModel tvShowDetailViewModel;
    private ActivityTvShowDetailsBinding activityTvShowDetailsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTvShowDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show_details);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        activityTvShowDetailsBinding.setIsLoading(true);
        tvShowDetailViewModel = new ViewModelProvider(this, factory).get(TvShowDetailViewModel.class);
        getShowId();
        tvShowDetailViewModel.getSpecificShow(tvShowId).observe(this, new Observer<TvShow>() {
            @Override
            public void onChanged(TvShow tvShow) {
                ArrayList<SlideModel> sliderImages = new ArrayList<>();
                activityTvShowDetailsBinding.setGenres(setGenres(tvShow.getTvShow().getGenres()));
                if (tvShow.getTvShow().getPictures()!=null){
                    for (String image : tvShow.getTvShow().getPictures()) {
                        sliderImages.add(new SlideModel(image, tvShow.getTvShow().getName()));
                    }
                    activityTvShowDetailsBinding.slider.setImageList(sliderImages, true);
                }
                activityTvShowDetailsBinding.setShowDetails(tvShow.getTvShow());
                activityTvShowDetailsBinding.setIsLoading(false);
            }
        });
    }

    private void getShowId() {
        Intent intent = getIntent();
        tvShowId = intent.getStringExtra(TV_SHOW);
    }


     public String setGenres( List<String> strings) {
        String genresString ="";
        for (String s : strings) {
            genresString = genresString+ s + " , ";
        }
        return genresString;
    }
}
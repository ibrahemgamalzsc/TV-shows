package com.example.tvshows.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tvshows.BaseApplication;
import com.example.tvshows.R;
import com.example.tvshows.data.pojo.mostPopularAndSearch.MostPopularAndSearch;
import com.example.tvshows.data.pojo.mostPopularAndSearch.Tv_shows;
import com.example.tvshows.databinding.ActivityMainBinding;
import com.example.tvshows.ui.adapter.ViewMostPopularAdapter;
import com.example.tvshows.ui.viewmodle.MainViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ViewMostPopularAdapter.OnMovieClickListener {

    public static final String TV_SHOW = "show";
    @Inject
    ViewModelProvider.Factory factory;
    private MainViewModel mainViewModel;
    private ViewMostPopularAdapter viewMostPopularAdapter;
    private ActivityMainBinding activityMainBinding;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ((BaseApplication) getApplication()).getAppComponent().inject(this);
        mainViewModel = new ViewModelProvider(this, factory).get(MainViewModel.class);
        activityMainBinding.setIsLoading(true);
        activityMainBinding.setMainViewModel(mainViewModel);
        getMostPopular();
        viewMostPopularAdapter = new ViewMostPopularAdapter(this);
        activityMainBinding.mostPopularRv.setAdapter(viewMostPopularAdapter);
    }

    private void getMostPopular() {
        activityMainBinding.setSearchEnabled(false);
        mainViewModel.getMostPopular().observe(this, new Observer<MostPopularAndSearch>() {
            @Override
            public void onChanged(MostPopularAndSearch mostPopularAndSearch) {
                activityMainBinding.setPageNumber(mostPopularAndSearch.getPage());
                if (Integer.valueOf(mostPopularAndSearch.getPage()) == 1) {
                    activityMainBinding.setPreviousEnable(false);
                } else {
                    activityMainBinding.setPreviousEnable(true);
                }
                if (Integer.valueOf(mostPopularAndSearch.getPage()) == Integer.valueOf(mostPopularAndSearch.getPages())) {
                    activityMainBinding.setNextEnable(false);
                } else {
                    activityMainBinding.setNextEnable(true);
                }
                viewMostPopularAdapter.submitList(mostPopularAndSearch.getTv_shows());
                viewMostPopularAdapter.notifyDataSetChanged();
                activityMainBinding.setIsLoading(false);
                activityMainBinding.setSearchEnabled(false);
            }
        });
    }

    @Override
    public void OnMovieClick(Tv_shows tv_shows) {
        Intent intent = new Intent(MainActivity.this, TvShowDetailsActivity.class);
        intent.putExtra(TV_SHOW, tv_shows.getId());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_toolbar_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
            searchView.setQueryHint("Enter TV show name");
            searchView.setMaxWidth(Integer.MAX_VALUE);
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.isEmpty() || query.equals("")) {
                    getMostPopular();
                } else {
                    activityMainBinding.setIsLoading(true);
                    mainViewModel.searchForShow(query).observe(MainActivity.this, new Observer<MostPopularAndSearch>() {
                        @Override
                        public void onChanged(MostPopularAndSearch mostPopularAndSearch) {
                            if (!(mostPopularAndSearch.getTotal().equals("0"))) {
                                activityMainBinding.setSearchPageNumber(mostPopularAndSearch.getPage());
                                viewMostPopularAdapter.submitList(mostPopularAndSearch.getTv_shows());
                                if (Integer.valueOf(mostPopularAndSearch.getPage()) == 1) {
                                    activityMainBinding.setPreviousSearchEnable(false);
                                } else {
                                    activityMainBinding.setPreviousSearchEnable(true);
                                }
                                if (Integer.valueOf(mostPopularAndSearch.getPage()) == Integer.valueOf(mostPopularAndSearch.getPages())) {
                                    activityMainBinding.setNextSearchEnable(false);
                                } else {
                                    activityMainBinding.setNextSearchEnable(true);
                                }
                                activityMainBinding.setIsLoading(false);
                                activityMainBinding.setSearchEnabled(true);
                                viewMostPopularAdapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty() || newText.equals("")) {
                    getMostPopular();
                }
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            searchView.onActionViewCollapsed();
        } else {
            super.onBackPressed();
        }
    }
}
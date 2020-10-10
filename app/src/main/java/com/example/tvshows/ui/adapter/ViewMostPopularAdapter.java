package com.example.tvshows.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshows.data.pojo.mostPopularAndSearch.Tv_shows;
import com.example.tvshows.databinding.TvShowHolderBinding;

public class ViewMostPopularAdapter extends ListAdapter<Tv_shows, ViewMostPopularAdapter.ShowViewHolder> {
    private OnMovieClickListener onMovieClickListener;

    public ViewMostPopularAdapter(OnMovieClickListener onMovieClickListener) {
        super(Tv_shows.callback);
        this.onMovieClickListener=onMovieClickListener;
    }

    @NonNull
    @Override
    public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TvShowHolderBinding tvShowHolderBinding = TvShowHolderBinding.inflate(layoutInflater, parent, false);
        tvShowHolderBinding.setShowClick(onMovieClickListener);
        return new ShowViewHolder(tvShowHolderBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowViewHolder holder, int position) {
        Tv_shows tvShows = getItem(position);
        holder.tvShowHolderBinding.setTvShows(tvShows);
    }

    public class ShowViewHolder extends RecyclerView.ViewHolder {

        private TvShowHolderBinding tvShowHolderBinding;

        public ShowViewHolder(@NonNull TvShowHolderBinding tvShowHolderBinding) {
            super(tvShowHolderBinding.getRoot());
            this.tvShowHolderBinding = tvShowHolderBinding;
        }
    }

    public interface OnMovieClickListener {
         void OnMovieClick(Tv_shows tv_shows);
    }
}

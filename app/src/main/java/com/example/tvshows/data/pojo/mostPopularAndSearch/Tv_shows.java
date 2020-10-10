package com.example.tvshows.data.pojo.mostPopularAndSearch;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class Tv_shows {
    private Object end_date;

    private String country;

    private String image_thumbnail_path;

    private String name;

    private String id;

    private String permalink;

    private String start_date;

    private String network;

    private String status;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getStart_date() {
        return start_date;
    }

    public Object getEnd_date() {
        return end_date;
    }

    public String getCountry() {
        return country;
    }

    public String getStatus() {
        return status;
    }

    public String getNetwork() {
        return network;
    }

    public String getImage_thumbnail_path() {
        return image_thumbnail_path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tv_shows tv_shows = (Tv_shows) o;
        return Objects.equals(end_date, tv_shows.end_date) &&
                Objects.equals(country, tv_shows.country) &&
                Objects.equals(image_thumbnail_path, tv_shows.image_thumbnail_path) &&
                Objects.equals(name, tv_shows.name) &&
                Objects.equals(id, tv_shows.id) &&
                Objects.equals(permalink, tv_shows.permalink) &&
                Objects.equals(start_date, tv_shows.start_date) &&
                Objects.equals(network, tv_shows.network) &&
                Objects.equals(status, tv_shows.status);
    }

    public static DiffUtil.ItemCallback<Tv_shows> callback = new DiffUtil.ItemCallback<Tv_shows>() {
        @Override
        public boolean areItemsTheSame(@NonNull Tv_shows oldItem, @NonNull Tv_shows newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Tv_shows oldItem, @NonNull Tv_shows newItem) {
            return oldItem.equals(newItem);
        }
    };

    @BindingAdapter("android:setImageSrc")
    public static void setImage(ImageView image, String imageUri) {
        Picasso.get().load(imageUri).into(image);
    }
}

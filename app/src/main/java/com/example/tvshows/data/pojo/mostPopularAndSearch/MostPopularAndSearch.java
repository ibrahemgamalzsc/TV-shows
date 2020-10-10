package com.example.tvshows.data.pojo.mostPopularAndSearch;

import java.util.List;

public class MostPopularAndSearch {
    private List<Tv_shows> tv_shows;

    private String total;

    private String pages;

    private String page;
    public String getTotal() {
        return total;
    }

    public String getPage() {
        return page;
    }

    public String getPages() {
        return pages;
    }

    public List<Tv_shows> getTv_shows() {
        return tv_shows;
    }
}

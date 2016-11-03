package com.thssh.rxjavaretrofit.bean.json.subclass;

import android.provider.MediaStore;

import java.util.List;

/**
 * Created by zhang on 2016/11/3.
 */
public class Subject {
    private Rating rating;
    private List<String> genres;
    private String title;
    private List<Cast> casts;
    private int collect_count;
    private String original_title;
    private String subtype;
    private List<Director> directors;
    private String year;
    private Images images;
    private String alt;
    private String id;

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public int getCollectCount() {
        return collect_count;
    }

    public void setCollectCount(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public void setOriginalTitle(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "rating=" + rating +
                ", genres=" + genres +
                ", title='" + title + '\'' +
                ", casts=" + casts +
                ", collect_count=" + collect_count +
                ", original_title='" + original_title + '\'' +
                ", subtype='" + subtype + '\'' +
                ", directors=" + directors +
                ", year='" + year + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

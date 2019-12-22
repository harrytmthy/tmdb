package com.harrytmthy.data.movie.model;

import com.google.gson.annotations.SerializedName;

import com.harrytmthy.domain.movie.model.Genre;
import com.harrytmthy.domain.movie.model.Video;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieResult, v 0.1 2019-12-11 11:04 by Harry Timothy
 */
@Getter @Setter
public class MovieResult {

    private float popularity;

    @SerializedName("poster_path") private String posterPath;

    private int id;

    @SerializedName("original_title") private String originalTitle;

    private String title;

    private String overview;

    @SerializedName("release_date") private String releaseDate;

    private int runtime;

    @SerializedName("vote_average") private float voteAverage;

    @SerializedName("genres") private List<Genre> genres;

    @SerializedName("videos") private Map<Object, List<Video>> videos;

}
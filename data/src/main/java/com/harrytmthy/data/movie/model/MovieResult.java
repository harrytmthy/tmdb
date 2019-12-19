package com.harrytmthy.data.movie.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieResult, v 0.1 2019-12-11 11:04 by Harry Timothy
 */
@Getter @Setter
public class MovieResult {

    private float popularity;

    @SerializedName("vote_count") private int voteCount;

    private boolean video;

    @SerializedName("poster_path") private String posterPath;

    private int id;

    private boolean adult;

    @SerializedName("backdrop_path") private String backdropPath;

    @SerializedName("original_language") private String originalLanguage;

    @SerializedName("original_title") private String originalTitle;

    @SerializedName("genre_ids") private int[] genreIds;

    private String title;

    @SerializedName("vote_average") private float voteAverage;

    private String overview;

    @SerializedName("release_date") private String releaseDate;

}

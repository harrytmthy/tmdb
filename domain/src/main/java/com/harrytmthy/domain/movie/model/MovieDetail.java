package com.harrytmthy.domain.movie.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetail, v 0.1 2019-12-19 14:27 by Harry Timothy
 */
@Getter @Setter
public class MovieDetail {

    private int id;

    private int runtime;

    private String originalTitle;

    private String overview;

    private String posterPath;

    private String releaseDate;

    private float popularity;

    private float voteAverage;

    private List<String> genres;

    private List<Video> videos;

}
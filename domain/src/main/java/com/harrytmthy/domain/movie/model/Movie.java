package com.harrytmthy.domain.movie.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version Movie, v 0.1 2019-12-11 14:47 by Harry Timothy
 */
@Getter @Setter
public class Movie {

    private int id;

    private String title;

    private String overview;

    private String posterPath;

    private String releaseDate;

}
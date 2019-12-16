package com.harrytmthy.domain.movie.model;

import lombok.Data;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version Movie, v 0.1 2019-12-11 14:47 by Harry Timothy
 */
@Data public class Movie {

    private int id;

    private String title;

    private String overview;

    private String posterPath;

    private String releaseDate;

}

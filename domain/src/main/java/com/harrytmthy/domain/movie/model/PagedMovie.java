package com.harrytmthy.domain.movie.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version PagedMovie, v 0.1 2019-12-11 14:50 by Harry Timothy
 */

@Getter @Setter
public class PagedMovie {

    private int page;

    private int totalPages;

    private List<Movie> movies;

}
package com.harrytmthy.domain.movie.model;

import java.util.List;

import lombok.Data;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version PagedMovie, v 0.1 2019-12-11 14:50 by Harry Timothy
 */

@Data public class PagedMovie {

    private int page;

    private int totalPages;

    private List<Movie> movies;

}
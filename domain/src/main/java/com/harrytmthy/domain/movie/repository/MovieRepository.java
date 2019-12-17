package com.harrytmthy.domain.movie.repository;

import com.harrytmthy.domain.movie.model.PagedMovie;

import io.reactivex.Observable;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieRepository, v 0.1 2019-12-11 14:52 by Harry Timothy
 */
public interface MovieRepository {

    Observable<PagedMovie> getPopularMovie(int page);

    Observable<PagedMovie> getTopRatedMovie(int page);

}
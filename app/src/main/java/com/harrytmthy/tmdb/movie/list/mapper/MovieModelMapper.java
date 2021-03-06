package com.harrytmthy.tmdb.movie.list.mapper;

import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.tmdb.base.BaseMapper;
import com.harrytmthy.tmdb.di.ActivityScope;
import com.harrytmthy.tmdb.movie.list.model.MovieState;

import javax.inject.Inject;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieModelMapper, v 0.1 2019-12-12 13:48 by Harry Timothy
 */
@ActivityScope
public class MovieModelMapper implements BaseMapper<PagedMovie, MovieState> {

    @Inject public MovieModelMapper() {}

    @Override
    public MovieState map(PagedMovie type) {
        return new MovieState.Data(type);
    }

}
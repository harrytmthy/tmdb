package com.harrytmthy.tmdb.movie.mapper;

import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.tmdb.base.BaseMapper;
import com.harrytmthy.tmdb.di.ActivityScope;
import com.harrytmthy.tmdb.movie.model.MovieState;

import javax.inject.Inject;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieModelMapper, v 0.1 2019-12-12 13:48 by Harry Timothy
 */
@ActivityScope
public class MovieModelMapper implements BaseMapper<PagedMovie, MovieState> {

    @Inject MovieModelMapper() {}

    @Override
    public MovieState mapToDataState(PagedMovie type) {
        return new MovieState.Data(type);
    }

    public MovieState mapToErrorState(Throwable throwable, int page) {
        return new MovieState.Error(throwable, page);
    }

    public MovieState toLoadingState() {
        return new MovieState.Loading();
    }

}
package com.harrytmthy.tmdb.movie.detail.mapper;

import com.harrytmthy.domain.account.model.Status;
import com.harrytmthy.domain.movie.model.MovieDetail;
import com.harrytmthy.tmdb.base.BaseMapper;
import com.harrytmthy.tmdb.di.ActivityScope;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailState;

import javax.inject.Inject;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetailModelMapper, v 0.1 2019-12-19 17:14 by Harry Timothy
 */
@ActivityScope
public class MovieDetailModelMapper implements BaseMapper<MovieDetail, MovieDetailState> {

    @Inject public MovieDetailModelMapper() {}

    @Override
    public MovieDetailState mapToDataState(MovieDetail type) {
        return new MovieDetailState.Data(type);
    }

    public MovieDetailState mapToFavoriteState(Status status) {
        return new MovieDetailState.Favorite(status);
    }

}
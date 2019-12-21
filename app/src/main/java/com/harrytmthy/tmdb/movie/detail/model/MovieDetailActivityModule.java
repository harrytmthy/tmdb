package com.harrytmthy.tmdb.movie.detail.model;

import com.harrytmthy.domain.movie.interactor.GetDetails;
import com.harrytmthy.tmdb.movie.detail.MovieDetailPresenter;
import com.harrytmthy.tmdb.movie.detail.mapper.MovieDetailModelMapper;

import dagger.Module;
import dagger.Provides;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetailActivityModule, v 0.1 2019-12-20 10:51 by Harry Timothy
 */
@Module
public abstract class MovieDetailActivityModule {

    @Provides
    static MovieDetailPresenter provideMoviePresenter(GetDetails getDetails,
        MovieDetailModelMapper movieDetailModelMapper) {
        return new MovieDetailPresenter(getDetails, movieDetailModelMapper);
    }

}
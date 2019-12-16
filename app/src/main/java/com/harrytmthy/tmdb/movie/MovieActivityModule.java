package com.harrytmthy.tmdb.movie;

import com.harrytmthy.domain.movie.interactor.GetPopularMovies;
import com.harrytmthy.tmdb.movie.mapper.MovieModelMapper;

import dagger.Module;
import dagger.Provides;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieActivityModule, v 0.1 2019-12-12 17:12 by Harry Timothy
 */
@Module
public abstract class MovieActivityModule {

    @Provides
    static MoviePresenter provideMoviePresenter(GetPopularMovies getPopularMovies,
        MovieModelMapper movieModelMapper) {
        return new MoviePresenter(getPopularMovies, movieModelMapper);
    }

}
package com.harrytmthy.tmdb.movie.list;

import com.harrytmthy.domain.account.interactor.GetFavoriteMovies;
import com.harrytmthy.domain.movie.interactor.GetPopularMovies;
import com.harrytmthy.domain.movie.interactor.GetTopRatedMovies;
import com.harrytmthy.tmdb.movie.list.mapper.MovieModelMapper;

import dagger.Module;
import dagger.Provides;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieActivityModule, v 0.1 2019-12-12 17:12 by Harry Timothy
 */
@Module
public abstract class MovieActivityModule {

    @Provides
    static MovieContract.Presenter provideMoviePresenter(GetPopularMovies getPopularMovies,
        GetTopRatedMovies getTopRatedMovies, GetFavoriteMovies getFavoriteMovies,
        MovieModelMapper movieModelMapper) {
        return new MoviePresenter(getPopularMovies, getTopRatedMovies, getFavoriteMovies,
            movieModelMapper);
    }

}
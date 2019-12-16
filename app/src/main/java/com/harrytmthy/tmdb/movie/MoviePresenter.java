package com.harrytmthy.tmdb.movie;

import com.harrytmthy.domain.movie.interactor.GetPopularMovies;
import com.harrytmthy.tmdb.base.BasePresenter;
import com.harrytmthy.tmdb.common.AppConstants;
import com.harrytmthy.tmdb.di.ActivityScope;
import com.harrytmthy.tmdb.movie.mapper.MovieModelMapper;
import com.harrytmthy.tmdb.movie.model.MovieAction;
import com.harrytmthy.tmdb.movie.model.MovieState;

import android.os.Handler;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MoviePresenter, v 0.1 2019-12-12 13:13 by Harry Timothy
 */
@ActivityScope
public class MoviePresenter extends BasePresenter<MovieAction, MovieState> {

    private final GetPopularMovies getPopularMoviesUseCase;

    private final MovieModelMapper movieModelMapper;

    private boolean canLoadNextPage;

    @Inject
    public MoviePresenter(GetPopularMovies getPopularMovies, MovieModelMapper movieModelMapper) {
        this.getPopularMoviesUseCase = getPopularMovies;
        this.movieModelMapper = movieModelMapper;
        canLoadNextPage = false;
    }

    @Override
    public ObservableTransformer<MovieAction, MovieState> dispatch() {
        return movieActionObservable -> movieActionObservable.switchMap(movieAction -> {
            if(movieAction instanceof MovieAction.LoadPopularMovies){
                return getPopularMoviesUseCase
                    .execute(((MovieAction.LoadPopularMovies) movieAction).page)
                    .map(this.movieModelMapper::mapToDataState)
                    .onErrorReturn(MovieState.Error::new);
            }
            return Observable.just(this.movieModelMapper.toLoadingState());
        });
    }

    public void refresh() {
        new Handler().postDelayed(() ->
            setAction(new MovieAction.LoadPopularMovies(1)),AppConstants.SWIPE_REFRESH_DELAY);
    }

    void setLoadNextPage() {
        this.canLoadNextPage = true;
    }

    public void nextPage() {
        if(!canLoadNextPage) return;
        MovieState currentState = state.get();
        if(currentState instanceof MovieState.Data) {
            int nextPage = ((MovieState.Data) currentState).data.getPage() + 1;
            setAction(new MovieAction.LoadPopularMovies(nextPage));
            canLoadNextPage = false;
        }
    }

}
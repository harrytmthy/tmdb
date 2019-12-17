package com.harrytmthy.tmdb.movie;

import com.harrytmthy.domain.movie.interactor.GetPopularMovies;
import com.harrytmthy.domain.movie.interactor.GetTopRatedMovies;
import com.harrytmthy.domain.movie.model.PagedMovie;
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

    private final GetTopRatedMovies getTopRatedMoviesUseCase;

    private final MovieModelMapper movieModelMapper;

    private MovieAction defaultAction;

    private boolean canLoadNextPage;

    @Inject
    public MoviePresenter(GetPopularMovies getPopularMovies,
        GetTopRatedMovies getTopRatedMovies,
        MovieModelMapper movieModelMapper) {
        this.getPopularMoviesUseCase = getPopularMovies;
        this.getTopRatedMoviesUseCase = getTopRatedMovies;
        this.movieModelMapper = movieModelMapper;
        defaultAction = new MovieAction.LoadPopularMovies(1);
        canLoadNextPage = false;
    }

    @Override
    public ObservableTransformer<MovieAction, MovieState> dispatch() {
        return movieActionObservable -> movieActionObservable.switchMap(movieAction -> {
            if(movieAction instanceof MovieAction.LoadPopularMovies) {
                final int page = ((MovieAction.LoadPopularMovies) movieAction).page;
                return handle(getPopularMoviesUseCase.execute(page), page);
            } else if (movieAction instanceof MovieAction.LoadTopRatedMovies) {
                final int page = ((MovieAction.LoadTopRatedMovies) movieAction).page;
                return handle(getTopRatedMoviesUseCase.execute(page), page);
            } else return Observable.just(this.movieModelMapper.toLoadingState());
        });
    }

    private Observable<MovieState> handle(Observable<PagedMovie> useCase, int page) {
        return useCase.map(this.movieModelMapper::mapToDataState)
            .onErrorReturn(throwable -> new MovieState.Error(throwable, page));
    }

    public void refresh() {
        new Handler().postDelayed(() ->
            doAction(defaultAction),AppConstants.SWIPE_REFRESH_DELAY);
    }

    void canLoadNextPage() {
        this.canLoadNextPage = true;
    }

    void setDefaultAction(MovieAction action) {
        this.defaultAction = action;
    }

    MovieAction getDefaultAction() {
        return this.defaultAction;
    }

    public void nextPage() {
        if(!canLoadNextPage) return;
        final MovieState currentState = state.get();
        final int currentPage;
        if(currentState instanceof MovieState.Data) {
            currentPage = ((MovieState.Data) currentState).data.getPage();
            if(currentPage == ((MovieState.Data) currentState).data.getTotalPages()) return;
        } else if(currentState instanceof MovieState.Error) {
            currentPage = ((MovieState.Error) currentState).page;
        } else currentPage = 0;
        if(defaultAction instanceof MovieAction.LoadPopularMovies) {
            doAction(new MovieAction.LoadPopularMovies(currentPage + 1));
        } else if (defaultAction instanceof MovieAction.LoadTopRatedMovies) {
            doAction(new MovieAction.LoadTopRatedMovies(currentPage + 1));
        }
        canLoadNextPage = false;
    }

}
package com.harrytmthy.tmdb.movie.list;

import com.harrytmthy.domain.account.interactor.GetFavoriteMovies;
import com.harrytmthy.domain.base.BaseUseCase;
import com.harrytmthy.domain.movie.interactor.GetPopularMovies;
import com.harrytmthy.domain.movie.interactor.GetTopRatedMovies;
import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.tmdb.base.BasePresenter;
import com.harrytmthy.tmdb.di.ActivityScope;
import com.harrytmthy.tmdb.movie.list.mapper.MovieModelMapper;
import com.harrytmthy.tmdb.movie.list.model.MovieAction;
import com.harrytmthy.tmdb.movie.list.model.MovieState;

import javax.inject.Inject;

import androidx.annotation.VisibleForTesting;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MoviePresenter, v 0.1 2019-12-12 13:13 by Harry Timothy
 */
@ActivityScope
public class MoviePresenter extends BasePresenter<MovieAction, MovieState> implements MovieContract.Presenter {

    private final GetPopularMovies getPopularMovies;

    private final GetTopRatedMovies getTopRatedMovies;

    private final GetFavoriteMovies getFavoriteMovies;

    private final MovieModelMapper movieModelMapper;

    @VisibleForTesting @Getter public BaseUseCase<PagedMovie, Integer> lastUseCase;

    @VisibleForTesting @Setter public boolean canLoadNextPage;

    @VisibleForTesting @Setter public int currentPage;

    @Inject
    public MoviePresenter(GetPopularMovies getPopularMovies, GetTopRatedMovies getTopRatedMovies,
        GetFavoriteMovies getFavoriteMovies, MovieModelMapper movieModelMapper) {
        this.getPopularMovies = getPopularMovies;
        this.getTopRatedMovies = getTopRatedMovies;
        this.getFavoriteMovies = getFavoriteMovies;
        this.movieModelMapper = movieModelMapper;
        canLoadNextPage = false;
        currentPage = 1;
    }

    @Override
    public ObservableTransformer<MovieAction, MovieState> dispatch() {
        return actionObservable -> actionObservable.switchMap(action -> {
            if (action instanceof MovieAction.LoadPopularMovies) {
                return handle(getPopularMovies, 1).startWith(new MovieState.Loading());
            } else if (action instanceof MovieAction.LoadTopRatedMovies) {
                return handle(getTopRatedMovies, 1).startWith(new MovieState.Loading());
            } else if (action instanceof MovieAction.LoadFavoriteMovies) {
                return handle(getFavoriteMovies, 1).startWith(new MovieState.Loading());
            } else if (action instanceof MovieAction.LoadNextPage) {
                return handle(lastUseCase, currentPage + 1);
            } else return handle(lastUseCase, 1); // Refresh
        });
    }

    private Observable<MovieState> handle(BaseUseCase<PagedMovie, Integer> useCase, int page) {
        canLoadNextPage = false;
        lastUseCase = useCase;
        return useCase.execute(page)
            .map(this.movieModelMapper::map)
            .onErrorReturn(MovieState.Error::new);
    }

    @Override
    public void loadNextPage() {
        if(!canLoadNextPage) return;
        doAction(new MovieAction.LoadNextPage());
    }

    @Override
    public void refresh() {
        doAction(new MovieAction.Refresh());
    }

}
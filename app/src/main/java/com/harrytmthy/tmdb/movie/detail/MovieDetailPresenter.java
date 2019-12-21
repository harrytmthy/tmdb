package com.harrytmthy.tmdb.movie.detail;

import com.harrytmthy.domain.movie.interactor.GetDetails;
import com.harrytmthy.domain.movie.model.MovieDetail;
import com.harrytmthy.tmdb.base.BasePresenter;
import com.harrytmthy.tmdb.di.ActivityScope;
import com.harrytmthy.tmdb.movie.detail.mapper.MovieDetailModelMapper;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailAction;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailState;

import javax.inject.Inject;

import androidx.databinding.ObservableField;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetailPresenter, v 0.1 2019-12-19 16:23 by Harry Timothy
 */
@ActivityScope
public class MovieDetailPresenter extends BasePresenter<MovieDetailAction, MovieDetailState> {

    private final GetDetails getDetailsUseCase;

    private final MovieDetailModelMapper movieDetailModelMapper;

    public final ObservableField<MovieDetail> movieDetail;

    @Inject
    public MovieDetailPresenter(GetDetails getDetails,
        MovieDetailModelMapper movieDetailModelMapper) {
        this.getDetailsUseCase = getDetails;
        this.movieDetailModelMapper = movieDetailModelMapper;
        this.movieDetail = new ObservableField<>();
    }

    @Override
    protected ObservableTransformer<MovieDetailAction, MovieDetailState> dispatch() {
        return actionObservable -> actionObservable.switchMap(action -> {
            if(action instanceof MovieDetailAction.LoadDetails) {
                final int movieId = ((MovieDetailAction.LoadDetails) action).movieId;
                return handle(getDetailsUseCase.execute(movieId))
                    .startWith(new MovieDetailState.Loading());
            } else return Observable.just(new MovieDetailState.Loading());
        });
    }

    private Observable<MovieDetailState> handle(Observable<MovieDetail> useCase) {
        return useCase.map(this.movieDetailModelMapper::mapToDataState)
            .onErrorReturn(MovieDetailState.Error::new);
    }

}
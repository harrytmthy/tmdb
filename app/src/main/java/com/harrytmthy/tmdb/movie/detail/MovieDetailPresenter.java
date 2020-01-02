package com.harrytmthy.tmdb.movie.detail;

import com.harrytmthy.data.constants.DataConstants;
import com.harrytmthy.domain.account.interactor.MarkFavorite;
import com.harrytmthy.domain.account.model.FavoriteParam;
import com.harrytmthy.domain.movie.interactor.GetDetails;
import com.harrytmthy.domain.movie.model.MovieDetail;
import com.harrytmthy.tmdb.base.BasePresenter;
import com.harrytmthy.tmdb.di.ActivityScope;
import com.harrytmthy.tmdb.movie.detail.mapper.MovieDetailModelMapper;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailAction;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailState;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetailPresenter, v 0.1 2019-12-19 16:23 by Harry Timothy
 */
@ActivityScope
public class MovieDetailPresenter extends BasePresenter<MovieDetailAction, MovieDetailState> implements MovieDetailContract.Presenter {

    private final GetDetails getDetails;

    private final MarkFavorite markFavorite;

    private final MovieDetailModelMapper movieDetailModelMapper;

    @Setter private int movieId;

    @Inject
    public MovieDetailPresenter(GetDetails getDetails, MarkFavorite markFavorite,
        MovieDetailModelMapper movieDetailModelMapper) {
        this.getDetails = getDetails;
        this.markFavorite = markFavorite;
        this.movieDetailModelMapper = movieDetailModelMapper;
    }

    @Override
    protected ObservableTransformer<MovieDetailAction, MovieDetailState> dispatch() {
        return actionObservable -> actionObservable.switchMap(action -> {
            if (action instanceof MovieDetailAction.LoadDetails) {
                return handle(getDetails.execute(movieId)).startWith(new MovieDetailState.Loading());
            } else if (action instanceof MovieDetailAction.MarkFavorite) {
                final FavoriteParam param = new FavoriteParam(true, movieId, DataConstants.DEFAULT_MEDIA_TYPE);
                return markFavorite.execute(param)
                    .map(status -> this.movieDetailModelMapper.mapToFavoriteState())
                    .onErrorReturn(MovieDetailState.Error::new);
            } else { // Unfavorite
                final FavoriteParam param = new FavoriteParam(false, movieId, DataConstants.DEFAULT_MEDIA_TYPE);
                return markFavorite.execute(param)
                    .map(status -> this.movieDetailModelMapper.mapToUnfavoriteState())
                    .onErrorReturn(MovieDetailState.Error::new);
            }
        });
    }

    private Observable<MovieDetailState> handle(Observable<MovieDetail> useCase) {
        return useCase.map(this.movieDetailModelMapper::map)
            .onErrorReturn(MovieDetailState.Error::new);
    }

    public void markFavorite(boolean favorite) {
        if(favorite) doAction(new MovieDetailAction.MarkFavorite());
        else doAction(new MovieDetailAction.Unfavorite());
    }

}
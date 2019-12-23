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
import lombok.Getter;
import lombok.Setter;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetailPresenter, v 0.1 2019-12-19 16:23 by Harry Timothy
 */
@ActivityScope
public class MovieDetailPresenter extends BasePresenter<MovieDetailAction, MovieDetailState> {

    private final GetDetails getDetails;

    private final MarkFavorite markFavorite;

    private final MovieDetailModelMapper movieDetailModelMapper;

    @Getter @Setter private boolean favorite;

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
                return handle(getDetails.execute(movieId))
                    .startWith(new MovieDetailState.Loading());
            } else { //Mark Favorite
                FavoriteParam param = new FavoriteParam();
                param.setFavorite(!favorite);
                param.setMediaId(movieId);
                param.setMediaType(DataConstants.DEFAULT_MEDIA_TYPE);
                return markFavorite.execute(param)
                    .map(this.movieDetailModelMapper::mapToFavoriteState)
                    .onErrorReturn(MovieDetailState.Error::new);
            }
        });
    }

    private Observable<MovieDetailState> handle(Observable<MovieDetail> useCase) {
        return useCase.map(this.movieDetailModelMapper::mapToDataState)
            .onErrorReturn(MovieDetailState.Error::new);
    }

}
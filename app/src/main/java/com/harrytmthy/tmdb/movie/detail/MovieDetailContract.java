package com.harrytmthy.tmdb.movie.detail;

import com.harrytmthy.domain.movie.model.MovieDetail;
import com.harrytmthy.tmdb.base.BaseContract;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailAction;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailState;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetailContract, v 0.1 2020-01-01 22:32 by Harry Timothy
 */
public interface MovieDetailContract {

    interface View extends BaseContract.BaseView<MovieDetailState> {

        void renderDataState(MovieDetail movieDetail);

        void renderErrorState(Throwable error);

        void renderFavoriteState();

        void renderUnfavoriteState();

    }

    interface Presenter extends BaseContract.BasePresenter<MovieDetailAction, MovieDetailState> {

        void markFavorite(boolean favorite);

        void setMovieId(int movieId);

    }

}
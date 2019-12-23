package com.harrytmthy.tmdb.movie.detail;

import com.harrytmthy.domain.account.model.Status;
import com.harrytmthy.domain.movie.model.MovieDetail;
import com.harrytmthy.tmdb.base.BaseView;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailState;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetailView, v 0.1 2019-12-20 09:58 by Harry Timothy
 */
public interface MovieDetailView <S extends MovieDetailState> extends BaseView<S> {

    void renderDataState(MovieDetail movieDetail);

    void renderErrorState(Throwable error);

    void renderFavoriteState(Status status);

}
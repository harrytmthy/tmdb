package com.harrytmthy.tmdb.movie;

import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.tmdb.base.BaseView;
import com.harrytmthy.tmdb.movie.model.MovieState;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieView, v 0.1 2019-12-14 07:36 by Harry Timothy
 *
 * @param <S> State type.
 */
public interface MovieView<S extends MovieState> extends BaseView<S> {

    void renderLoadingState();

    void renderDataState(PagedMovie pagedMovie);

    void renderErrorState(Throwable error);

}
package com.harrytmthy.tmdb.movie.list;

import com.harrytmthy.domain.base.BaseUseCase;
import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.tmdb.base.BaseContract;
import com.harrytmthy.tmdb.movie.list.model.MovieAction;
import com.harrytmthy.tmdb.movie.list.model.MovieState;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieContract, v 0.1 2019-12-31 10:43 by Harry Timothy
 */
public interface MovieContract {

    interface View extends BaseContract.BaseView<MovieState> {

        void renderDataState(PagedMovie pagedMovie);

        void renderErrorState(Throwable error);

    }

    interface Presenter extends BaseContract.BasePresenter<MovieAction, MovieState> {

        void loadNextPage();

        void refresh();

        BaseUseCase<PagedMovie, Integer> getLastUseCase();

        void setCurrentPage(int page);

        void setCanLoadNextPage(boolean canLoadNextPage);

    }

}
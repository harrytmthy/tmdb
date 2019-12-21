package com.harrytmthy.tmdb.movie.detail.model;

import com.harrytmthy.domain.movie.model.MovieDetail;
import com.harrytmthy.tmdb.base.BaseState;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetailState, v 0.1 2019-12-19 16:39 by Harry Timothy
 */
public interface MovieDetailState extends BaseState {

    final class Loading implements MovieDetailState {}

    final class Data implements MovieDetailState {

        public MovieDetail data;

        public Data(MovieDetail data) {
            this.data = data;
        }

    }

    final class Error implements MovieDetailState {

        public Throwable error;

        public Error(Throwable error) {
            this.error = error;
        }

    }

}
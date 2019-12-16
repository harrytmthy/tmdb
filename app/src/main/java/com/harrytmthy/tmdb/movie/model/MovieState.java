package com.harrytmthy.tmdb.movie.model;

import com.harrytmthy.domain.movie.model.Movie;
import com.harrytmthy.tmdb.base.BaseState;

import java.util.List;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieState, v 0.1 2019-12-15 15:04 by Harry Timothy
 */
public interface MovieState extends BaseState {

    final class Loading implements MovieState {}

    final class Data implements MovieState {

        public List<Movie> data;

        public Data(List<Movie> data) {
            this.data = data;
        }

    }

    final class Error implements MovieState {

        public Throwable error;

        public Error(Throwable error) {
            this.error = error;
        }

    }

}
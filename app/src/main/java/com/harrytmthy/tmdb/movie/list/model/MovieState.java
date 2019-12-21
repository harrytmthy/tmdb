package com.harrytmthy.tmdb.movie.list.model;

import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.tmdb.base.BaseState;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieState, v 0.1 2019-12-15 15:04 by Harry Timothy
 */
public interface MovieState extends BaseState {

    final class Loading implements MovieState {}

    final class Refreshing implements MovieState {}

    final class Data implements MovieState {

        public PagedMovie data;

        public Data(PagedMovie data) {
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
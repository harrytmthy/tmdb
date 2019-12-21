package com.harrytmthy.tmdb.movie.detail.model;

import com.harrytmthy.tmdb.base.BaseAction;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetailAction, v 0.1 2019-12-19 16:32 by Harry Timothy
 */
public interface MovieDetailAction extends BaseAction {

    final class Initial implements MovieDetailAction {}

    final class LoadDetails implements MovieDetailAction {

        public final int movieId;

        public LoadDetails(int movieId) {
            this.movieId = movieId;
        }

    }

    final class MarkFavorite implements MovieDetailAction {}

}
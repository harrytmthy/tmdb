package com.harrytmthy.tmdb.movie.list.model;

import com.harrytmthy.tmdb.base.BaseAction;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieAction, v 0.1 2019-12-13 16:37 by Harry Timothy
 */
public interface MovieAction extends BaseAction {

    final class LoadPopularMovies implements MovieAction {}

    final class LoadTopRatedMovies implements MovieAction {}

    final class LoadFavoriteMovies implements MovieAction {}

    final class LoadNextPage implements MovieAction {}

    final class Refresh implements MovieAction {}

}
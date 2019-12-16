package com.harrytmthy.tmdb.movie.model;

import com.harrytmthy.tmdb.base.BaseAction;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieAction, v 0.1 2019-12-13 16:37 by Harry Timothy
 */
public interface MovieAction extends BaseAction {

    final class Initial implements MovieAction {}

    final class LoadPopularMovies implements MovieAction {}

}

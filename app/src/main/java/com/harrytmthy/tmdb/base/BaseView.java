package com.harrytmthy.tmdb.base;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version BaseView, v 0.1 2019-12-13 14:37 by Harry Timothy
 *
 * @param <S> State type.
 */
public interface BaseView<S extends BaseState> {

    void render(S state);

}
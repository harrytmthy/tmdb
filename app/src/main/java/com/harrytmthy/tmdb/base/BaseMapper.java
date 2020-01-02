package com.harrytmthy.tmdb.base;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version BaseMapper, v 0.1 2019-12-12 13:43 by Harry Timothy
 *
 * @param <D> Domain model output type.
 * @param <S> State type.
 */
public interface BaseMapper<D, S extends BaseState> {

    S map(D type);

}
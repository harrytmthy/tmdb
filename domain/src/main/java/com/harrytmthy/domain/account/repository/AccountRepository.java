package com.harrytmthy.domain.account.repository;

import com.harrytmthy.domain.account.model.FavoriteParam;
import com.harrytmthy.domain.account.model.Status;
import com.harrytmthy.domain.movie.model.PagedMovie;

import io.reactivex.Observable;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AccountRepository, v 0.1 2019-12-22 21:18 by Harry Timothy
 */
public interface AccountRepository {

    Observable<Status> markFavorite(FavoriteParam param);

    Observable<PagedMovie> getFavoriteMovies(int page);

}
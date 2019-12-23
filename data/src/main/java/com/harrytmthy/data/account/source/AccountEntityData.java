package com.harrytmthy.data.account.source;

import com.harrytmthy.data.account.model.StatusResult;
import com.harrytmthy.data.common.PagedResult;
import com.harrytmthy.data.constants.DataConstants;
import com.harrytmthy.data.movie.model.MovieResult;
import com.harrytmthy.domain.account.model.FavoriteParam;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AccountEntityData, v 0.1 2019-12-22 20:01 by Harry Timothy
 */
public interface AccountEntityData {

    @POST(DataConstants.API_MARK_FAVORITE)
    Observable<StatusResult> markFavorite(@Body FavoriteParam param);

    @GET(DataConstants.API_MOVIE_FAVORITE)
    Observable<PagedResult<MovieResult>> getFavoriteMovies(@Query(DataConstants.PARAM_PAGE) int page);

}
package com.harrytmthy.data.movie.source;

import com.harrytmthy.data.common.PagedResult;
import com.harrytmthy.data.constants.DataConstants;
import com.harrytmthy.data.movie.model.MovieResult;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieEntityData, v 0.1 2019-12-11 11:15 by Harry Timothy
 */
public interface MovieEntityData {

    @GET(DataConstants.API_MOVIE_DETAIL)
    Observable<MovieResult> getDetails(
        @Path(DataConstants.PARAM_MOVIE_ID) int movieId,
        @Query(DataConstants.PARAM_APPEND_RESPONSE) String appendResponse
    );

    @GET(DataConstants.API_MOVIE_POPULAR)
    Observable<PagedResult<MovieResult>> getPopularMovie(
        @Query(DataConstants.PARAM_PAGE) int page
    );

    @GET(DataConstants.API_MOVIE_TOP_RATED)
    Observable<PagedResult<MovieResult>> getTopRatedMovie(
        @Query(DataConstants.PARAM_PAGE) int page
    );

}
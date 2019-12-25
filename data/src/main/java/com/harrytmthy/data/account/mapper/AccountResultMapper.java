package com.harrytmthy.data.account.mapper;

import com.harrytmthy.data.account.model.StatusResult;
import com.harrytmthy.data.common.PagedResult;
import com.harrytmthy.data.movie.model.MovieResult;
import com.harrytmthy.domain.account.model.Status;
import com.harrytmthy.domain.movie.model.Movie;
import com.harrytmthy.domain.movie.model.PagedMovie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AccountResultMapper, v 0.1 2019-12-22 20:21 by Harry Timothy
 */
@Singleton
public class AccountResultMapper {

    @Inject public AccountResultMapper() {}

    public Status map(StatusResult statusResult) {
        if(statusResult == null) return null;
        final Status status = new Status();
        status.setCode(statusResult.getStatusCode());
        status.setMessage(statusResult.getStatusMessge());
        return status;
    }

    public PagedMovie map(PagedResult<MovieResult> pagedMovieResult) {
        if(pagedMovieResult == null) return null;
        final PagedMovie pagedMovie = new PagedMovie();
        pagedMovie.setPage(pagedMovieResult.getPage());
        pagedMovie.setTotalPages(pagedMovieResult.getTotalPages());
        final List<Movie> movies = new ArrayList<>();
        for(final MovieResult movieResult : pagedMovieResult.getResults()) {
            final Movie movie = new Movie();
            movie.setId(movieResult.getId());
            movie.setPosterPath(movieResult.getPosterPath());
            movies.add(movie);
        }
        pagedMovie.setMovies(movies);
        return pagedMovie;
    }

}
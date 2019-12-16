package com.harrytmthy.data.movie.mapper;

import com.harrytmthy.data.common.PagedResult;
import com.harrytmthy.data.movie.model.MovieResult;
import com.harrytmthy.domain.movie.model.Movie;
import com.harrytmthy.domain.movie.model.PagedMovie;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieResultMapper, v 0.1 2019-12-11 16:16 by Harry Timothy
 */
@Singleton
public class MovieResultMapper {

    @Inject public MovieResultMapper() {}

    public PagedMovie map(PagedResult<MovieResult> pagedMovieResult) {
        if(pagedMovieResult == null) return null;
        PagedMovie pagedMovie = new PagedMovie();
        pagedMovie.setPage(pagedMovieResult.getPage());
        pagedMovie.setTotalPages(pagedMovieResult.getTotalPages());
        List<Movie> movies = new ArrayList<>();
        for(MovieResult movieResult : pagedMovieResult.getResults()) {
            Movie movie = new Movie();
            movie.setId(movieResult.getId());
            movie.setTitle(movieResult.getTitle());
            movie.setOverview(movieResult.getOverview());
            movie.setPosterPath(movieResult.getPosterPath());
            movie.setReleaseDate(movieResult.getReleaseDate());
            movies.add(movie);
        }
        pagedMovie.setMovies(movies);
        return pagedMovie;
    }

}

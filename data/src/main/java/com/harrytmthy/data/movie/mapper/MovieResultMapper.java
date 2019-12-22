package com.harrytmthy.data.movie.mapper;

import com.harrytmthy.data.common.PagedResult;
import com.harrytmthy.data.movie.model.MovieResult;
import com.harrytmthy.domain.movie.model.Genre;
import com.harrytmthy.domain.movie.model.Movie;
import com.harrytmthy.domain.movie.model.MovieDetail;
import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.domain.movie.model.Video;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            movie.setPosterPath(movieResult.getPosterPath());
            movies.add(movie);
        }
        pagedMovie.setMovies(movies);
        return pagedMovie;
    }

    public MovieDetail map(MovieResult movieResult) {
        if(movieResult == null) return null;
        MovieDetail movieDetail = new MovieDetail();
        movieDetail.setId(movieResult.getId());
        movieDetail.setOriginalTitle(movieResult.getOriginalTitle());
        movieDetail.setOverview(movieResult.getOverview());
        movieDetail.setRuntime(movieResult.getRuntime());
        movieDetail.setReleaseDate(movieResult.getReleaseDate());
        movieDetail.setPopularity(movieResult.getPopularity());
        movieDetail.setVoteAverage(movieResult.getVoteAverage());
        movieDetail.setPosterPath(movieResult.getPosterPath());
        List<String> genres = new ArrayList<>();
        for(Genre genre : movieResult.getGenres()) {
            genres.add(genre.getName());
        }
        movieDetail.setGenres(genres);
        for(Map.Entry<Object, List<Video>> entry : movieResult.getVideos().entrySet()) {
            movieDetail.setVideos(entry.getValue());
        }
        return movieDetail;
    }

}
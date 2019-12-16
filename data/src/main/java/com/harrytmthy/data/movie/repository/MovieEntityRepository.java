package com.harrytmthy.data.movie.repository;

import com.harrytmthy.data.movie.mapper.MovieResultMapper;
import com.harrytmthy.data.movie.source.MovieEntityDataFactory;
import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.domain.movie.repository.MovieRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieEntityRepository, v 0.1 2019-12-11 11:33 by Harry Timothy
 */
@Singleton
public class MovieEntityRepository implements MovieRepository {

    private final MovieEntityDataFactory movieEntityDataFactory;
    private final MovieResultMapper movieResultMapper;

    @Inject
    public MovieEntityRepository(MovieEntityDataFactory movieEntityDataFactory,
                                 MovieResultMapper movieResultMapper) {
        this.movieEntityDataFactory = movieEntityDataFactory;
        this.movieResultMapper = movieResultMapper;
    }

    @Override
    public Observable<PagedMovie> getPopularMovie() {
        return movieEntityDataFactory.createService().getPopularMovie().map(movieResultMapper::map);
    }
}

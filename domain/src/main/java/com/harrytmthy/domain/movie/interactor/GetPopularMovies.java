package com.harrytmthy.domain.movie.interactor;

import com.harrytmthy.domain.base.BaseUseCase;
import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;
import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.domain.movie.repository.MovieRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version GetPopularMovies, v 0.1 2019-12-11 17:12 by Harry Timothy
 */
public class GetPopularMovies extends BaseUseCase<PagedMovie, Integer> {

    private final MovieRepository movieRepository;

    @Inject public GetPopularMovies(MovieRepository movieRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    public Observable<PagedMovie> buildUseCaseObservable(Integer page) {
        return movieRepository.getPopularMovie(page);
    }

}
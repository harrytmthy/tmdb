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
 * @version GetTopRatedMovies, v 0.1 2019-17-11 12:01 by Harry Timothy
 */
public class GetTopRatedMovies extends BaseUseCase<PagedMovie, Integer> {

    private final MovieRepository movieRepository;

    @Inject public GetTopRatedMovies(MovieRepository movieRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    public Observable<PagedMovie> buildUseCaseObservable(Integer page) {
        return movieRepository.getTopRatedMovie(page);
    }

}
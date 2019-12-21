package com.harrytmthy.domain.movie.interactor;

import com.harrytmthy.domain.base.BaseUseCase;
import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;
import com.harrytmthy.domain.movie.model.MovieDetail;
import com.harrytmthy.domain.movie.repository.MovieRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version GetDetails, v 0.1 2019-12-19 15:09 by Harry Timothy
 */
public class GetDetails extends BaseUseCase<MovieDetail, Integer> {

    private final MovieRepository movieRepository;

    @Inject
    public GetDetails(MovieRepository movieRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.movieRepository = movieRepository;
    }

    @Override
    public Observable<MovieDetail> buildUseCaseObservable(Integer movieId) {
        return movieRepository.getDetails(movieId);
    }

}
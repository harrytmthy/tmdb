package com.harrytmthy.domain.account.interactor;

import com.harrytmthy.domain.account.repository.AccountRepository;
import com.harrytmthy.domain.base.BaseUseCase;
import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;
import com.harrytmthy.domain.movie.model.PagedMovie;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version GetFavoriteMovies, v 0.1 2019-12-22 22:48 by Harry Timothy
 */
public class GetFavoriteMovies extends BaseUseCase<PagedMovie, Integer> {

    private final AccountRepository accountRepository;

    @Inject
    public GetFavoriteMovies(AccountRepository accountRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.accountRepository = accountRepository;
    }

    @Override
    public Observable<PagedMovie> buildUseCaseObservable(Integer page) {
        return accountRepository.getFavoriteMovies(page);
    }

}
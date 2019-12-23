package com.harrytmthy.domain.account.interactor;

import com.harrytmthy.domain.account.model.FavoriteParam;
import com.harrytmthy.domain.account.model.Status;
import com.harrytmthy.domain.account.repository.AccountRepository;
import com.harrytmthy.domain.base.BaseUseCase;
import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MarkFavorite, v 0.1 2019-12-22 21:45 by Harry Timothy
 */
public class MarkFavorite extends BaseUseCase<Status, FavoriteParam> {

    private final AccountRepository accountRepository;

    @Inject
    public MarkFavorite(AccountRepository accountRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.accountRepository = accountRepository;
    }

    @Override
    public Observable<Status> buildUseCaseObservable(FavoriteParam favoriteParam) {
        return accountRepository.markFavorite(favoriteParam);
    }

}
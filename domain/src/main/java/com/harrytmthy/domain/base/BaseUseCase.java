package com.harrytmthy.domain.base;

import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version BaseUseCase, v 0.1 2019-12-11 16:57 by Harry Timothy
 */
public abstract class BaseUseCase<T, Params> {

    private final ThreadExecutor threadExecutor;

    private final PostExecutionThread postExecutionThread;

    protected BaseUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    protected abstract Observable<T> buildUseCaseObservable(Params params);

    public Observable<T> execute(Params params) {
        return this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler());
    }

}
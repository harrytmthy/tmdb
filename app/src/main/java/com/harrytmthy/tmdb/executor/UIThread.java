package com.harrytmthy.tmdb.executor;

import com.harrytmthy.domain.executor.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version UIThread, v 0.1 2019-12-12 21:35 by Harry Timothy
 */
@Singleton
public class UIThread implements PostExecutionThread {

    @Inject
    UIThread() {}

    @Override public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
package com.harrytmthy.domain.executor;

import io.reactivex.Scheduler;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version PostExecutionThread, v 0.1 2019-12-11 17:03 by Harry Timothy
 */
public interface PostExecutionThread {

    Scheduler getScheduler();

}

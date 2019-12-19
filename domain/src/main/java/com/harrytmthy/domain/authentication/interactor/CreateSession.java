package com.harrytmthy.domain.authentication.interactor;

import com.harrytmthy.domain.authentication.model.Auth;
import com.harrytmthy.domain.authentication.model.SessionParam;
import com.harrytmthy.domain.authentication.repository.AuthRepository;
import com.harrytmthy.domain.base.BaseUseCase;
import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version CreateSession, v 0.1 2019-12-17 15:11 by Harry Timothy
 */
public class CreateSession extends BaseUseCase<Auth, SessionParam> {

    private final AuthRepository authRepository;

    @Inject public CreateSession(AuthRepository authRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.authRepository = authRepository;
    }

    @Override
    public Observable<Auth> buildUseCaseObservable(SessionParam param) {
        return authRepository.createSession(param);
    }

}
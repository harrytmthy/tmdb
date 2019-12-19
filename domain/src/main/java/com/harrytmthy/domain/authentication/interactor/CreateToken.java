package com.harrytmthy.domain.authentication.interactor;

import com.harrytmthy.domain.authentication.model.Auth;
import com.harrytmthy.domain.authentication.repository.AuthRepository;
import com.harrytmthy.domain.base.BaseUseCase;
import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version CreateToken, v 0.1 2019-12-17 15:02 by Harry Timothy
 */
public class CreateToken extends BaseUseCase<Auth, Void> {

    private final AuthRepository authRepository;

    @Inject public CreateToken(AuthRepository authRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.authRepository = authRepository;
    }

    @Override
    public Observable<Auth> buildUseCaseObservable(Void params) {
        return authRepository.createToken();
    }

}
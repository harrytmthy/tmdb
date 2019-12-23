package com.harrytmthy.domain.authentication.interactor;

import com.harrytmthy.domain.authentication.model.Auth;
import com.harrytmthy.domain.authentication.model.TokenParam;
import com.harrytmthy.domain.authentication.repository.AuthRepository;
import com.harrytmthy.domain.base.BaseUseCase;
import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version ValidateToken, v 0.1 2019-12-17 15:08 by Harry Timothy
 */
public class ValidateToken extends BaseUseCase<Auth, TokenParam> {

    private final AuthRepository authRepository;

    @Inject public ValidateToken(AuthRepository authRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.authRepository = authRepository;
    }

    @Override
    public Observable<Auth> buildUseCaseObservable(TokenParam param) {
        return authRepository.validateToken(param);
    }

}
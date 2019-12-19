package com.harrytmthy.data.authentication.repository;

import com.harrytmthy.data.authentication.mapper.AuthResultMapper;
import com.harrytmthy.data.authentication.source.AuthEntityDataFactory;
import com.harrytmthy.domain.authentication.model.Auth;
import com.harrytmthy.domain.authentication.model.SessionParam;
import com.harrytmthy.domain.authentication.model.TokenParam;
import com.harrytmthy.domain.authentication.repository.AuthRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthEntityRepository, v 0.1 2019-12-17 14:43 by Harry Timothy
 */
@Singleton
public class AuthEntityRepository implements AuthRepository {

    private final AuthEntityDataFactory authEntityDataFactory;

    private final AuthResultMapper authResultMapper;

    @Inject public AuthEntityRepository(AuthEntityDataFactory authEntityDataFactory,
        AuthResultMapper authResultMapper) {
        this.authEntityDataFactory = authEntityDataFactory;
        this.authResultMapper = authResultMapper;
    }

    @Override
    public Observable<Auth> createToken() {
        return authEntityDataFactory.createService()
            .createToken()
            .map(authResultMapper::map);
    }

    @Override
    public Observable<Auth> validateToken(TokenParam params) {
        return authEntityDataFactory.createService()
            .validateToken(params)
            .map(authResultMapper::map);
    }

    @Override
    public Observable<Auth> createSession(SessionParam params) {
        return authEntityDataFactory.createService()
            .createSession(params)
            .map(authResultMapper::map);
    }

}
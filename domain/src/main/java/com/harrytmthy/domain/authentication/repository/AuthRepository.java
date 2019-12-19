package com.harrytmthy.domain.authentication.repository;

import com.harrytmthy.domain.authentication.model.SessionParam;
import com.harrytmthy.domain.authentication.model.Auth;
import com.harrytmthy.domain.authentication.model.TokenParam;

import io.reactivex.Observable;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthRepository, v 0.1 2019-12-17 14:31 by Harry Timothy
 */
public interface AuthRepository {

    Observable<Auth> createToken();

    Observable<Auth> validateToken(TokenParam params);

    Observable<Auth> createSession(SessionParam params);

}
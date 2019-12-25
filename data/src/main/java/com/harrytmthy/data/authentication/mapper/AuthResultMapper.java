package com.harrytmthy.data.authentication.mapper;

import com.harrytmthy.data.authentication.model.SessionResult;
import com.harrytmthy.data.authentication.model.TokenResult;
import com.harrytmthy.domain.authentication.model.Auth;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthResultMapper, v 0.1 2019-12-17 14:49 by Harry Timothy
 */
@Singleton
public class AuthResultMapper {

    @Inject public AuthResultMapper() {}

    public Auth map(TokenResult tokenResult) {
        if(tokenResult == null) return null;
        final Auth auth = new Auth();
        auth.setRequestToken(tokenResult.getRequestToken());
        return auth;
    }

    public Auth map(SessionResult sessionResult) {
        if(sessionResult == null) return null;
        final Auth auth = new Auth();
        auth.setSessionId(sessionResult.getSessionId());
        return auth;
    }

}
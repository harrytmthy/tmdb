package com.harrytmthy.tmdb.authentication.mapper;

import com.harrytmthy.domain.authentication.model.Auth;
import com.harrytmthy.tmdb.authentication.model.AuthState;
import com.harrytmthy.tmdb.base.BaseMapper;
import com.harrytmthy.tmdb.di.ActivityScope;

import javax.inject.Inject;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthModelMapper, v 0.1 2019-12-17 15:37 by Harry Timothy
 */
@ActivityScope
public class AuthModelMapper implements BaseMapper<Auth, AuthState> {

    @Inject public AuthModelMapper() {}

    @Override
    public AuthState mapToDataState(Auth type) {
        return new AuthState.Data(type);
    }

    public AuthState toLoadingState() {
        return new AuthState.Loading();
    }

}
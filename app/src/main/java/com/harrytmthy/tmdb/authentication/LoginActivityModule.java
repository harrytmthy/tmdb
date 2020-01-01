package com.harrytmthy.tmdb.authentication;

import com.harrytmthy.domain.authentication.interactor.CreateSession;
import com.harrytmthy.domain.authentication.interactor.CreateToken;
import com.harrytmthy.domain.authentication.interactor.ValidateToken;
import com.harrytmthy.tmdb.authentication.mapper.AuthModelMapper;
import com.harrytmthy.tmdb.authentication.model.LoginContract;

import dagger.Module;
import dagger.Provides;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version LoginActivityModule, v 0.1 2019-12-17 18:12 by Harry Timothy
 */
@Module
public abstract class LoginActivityModule {

    @Provides static LoginContract.Presenter provideAuthPresenter(CreateSession createSession,
        CreateToken createToken, ValidateToken validateToken, AuthModelMapper authModelMapper) {
        return new LoginPresenter(createSession, createToken, validateToken, authModelMapper);
    }

}

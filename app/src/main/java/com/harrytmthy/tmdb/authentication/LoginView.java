package com.harrytmthy.tmdb.authentication;

import com.harrytmthy.domain.authentication.model.Auth;
import com.harrytmthy.tmdb.authentication.model.AuthState;
import com.harrytmthy.tmdb.base.BaseView;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version LoginView, v 0.1 2019-12-17 18:22 by Harry Timothy
 */
public interface LoginView<S extends AuthState> extends BaseView<S> {

    void onRegisterClicked();

    void renderDataState(Auth auth);

    void renderErrorState(Throwable error);

}

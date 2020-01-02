package com.harrytmthy.tmdb.authentication.model;

import com.harrytmthy.domain.authentication.model.Auth;
import com.harrytmthy.tmdb.base.BaseContract;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version LoginContract, v 0.1 2020-01-01 22:46 by Harry Timothy
 */
public interface LoginContract {

    interface View extends BaseContract.BaseView<AuthState> {

        void renderLoginState(Auth auth);

        void renderRegisterState();

        void renderErrorState(Throwable error);

    }

    interface Presenter extends BaseContract.BasePresenter<AuthAction, AuthState> {

        void login();

        void register();

        String getUsername();

        void setUsername(String username);

        void setPassword(CharSequence s, int start, int before, int count);

    }

}
package com.harrytmthy.tmdb.authentication.model;

import com.harrytmthy.domain.authentication.model.Auth;
import com.harrytmthy.tmdb.base.BaseState;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthState, v 0.1 2019-12-17 15:17 by Harry Timothy
 */
public interface AuthState extends BaseState {

    final class Loading implements AuthState {}

    final class Data implements AuthState {

        public Auth data;

        public Data(Auth data) {
            this.data = data;
        }

    }

    final class Error implements AuthState {

        public Throwable error;

        public Error(Throwable error) {
            this.error = error;
        }

    }

}
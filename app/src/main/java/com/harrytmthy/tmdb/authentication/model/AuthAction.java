package com.harrytmthy.tmdb.authentication.model;

import com.harrytmthy.tmdb.base.BaseAction;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthAction, v 0.1 2019-12-17 15:26 by Harry Timothy
 */
public interface AuthAction extends BaseAction {

    final class Initial implements AuthAction {}

    final class Login implements AuthAction {}

    final class Register implements AuthAction {}

}
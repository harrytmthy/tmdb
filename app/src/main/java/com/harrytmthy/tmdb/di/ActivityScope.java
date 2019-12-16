package com.harrytmthy.tmdb.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version ActivityScope, v 0.1 2019-12-12 13:18 by Harry Timothy
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {}
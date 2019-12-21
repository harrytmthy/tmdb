package com.harrytmthy.tmdb.di.module;

import com.harrytmthy.tmdb.authentication.LoginActivity;
import com.harrytmthy.tmdb.authentication.LoginActivityModule;
import com.harrytmthy.tmdb.di.ActivityScope;
import com.harrytmthy.tmdb.movie.detail.MovieDetailActivity;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailActivityModule;
import com.harrytmthy.tmdb.movie.list.MovieActivity;
import com.harrytmthy.tmdb.movie.list.MovieActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AndroidInjectBuilder, v 0.1 2019-12-12 16:36 by Harry Timothy
 */
@Module
public abstract class AndroidInjectBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = MovieActivityModule.class)
    abstract MovieActivity bindMovieActivity();

    @ActivityScope
    @ContributesAndroidInjector(modules = MovieDetailActivityModule.class)
    abstract MovieDetailActivity bindMovieDetailActivity();

}
package com.harrytmthy.tmdb.di.module;

import com.harrytmthy.tmdb.di.ActivityScope;
import com.harrytmthy.tmdb.movie.MovieActivity;
import com.harrytmthy.tmdb.movie.MovieActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AndroidInjectBuilder, v 0.1 2019-12-12 16:36 by Harry Timothy
 */
@Module
public abstract class AndroidInjectBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = MovieActivityModule.class)
    abstract MovieActivity bindMovieActivity();

}

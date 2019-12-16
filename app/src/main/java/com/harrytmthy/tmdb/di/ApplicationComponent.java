package com.harrytmthy.tmdb.di;

import com.harrytmthy.tmdb.TmdbApplication;
import com.harrytmthy.tmdb.di.module.AndroidInjectBuilder;
import com.harrytmthy.tmdb.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version ApplicationComponent, v 0.1 2019-12-12 15:06 by Harry Timothy
 */
@Singleton
@Component(modules = {
    AndroidSupportInjectionModule.class,
    AndroidInjectBuilder.class,
    ApplicationModule.class
})
public interface ApplicationComponent extends AndroidInjector<TmdbApplication> {
    @Component.Factory
    interface Factory extends AndroidInjector.Factory<TmdbApplication> {}
}
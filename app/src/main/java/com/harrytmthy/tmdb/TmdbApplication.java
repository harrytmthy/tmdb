package com.harrytmthy.tmdb;

import com.harrytmthy.tmdb.di.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version TmdbApplication, v 0.1 2019-12-12 15:14 by Harry Timothy
 */
public class TmdbApplication extends DaggerApplication {

    @Override
    public AndroidInjector<TmdbApplication> applicationInjector() {
        return DaggerApplicationComponent.factory().create(this);
    }

}

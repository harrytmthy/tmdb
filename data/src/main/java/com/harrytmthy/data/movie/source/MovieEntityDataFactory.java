package com.harrytmthy.data.movie.source;

import com.harrytmthy.data.base.BaseEntityDataFactory;

import javax.inject.Singleton;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieEntityDataFactory, v 0.1 2019-12-11 11:23 by Harry Timothy
 */
@Singleton
public class MovieEntityDataFactory extends BaseEntityDataFactory {

    public MovieEntityData createService() {
        return retrofit.create(MovieEntityData.class);
    }

}
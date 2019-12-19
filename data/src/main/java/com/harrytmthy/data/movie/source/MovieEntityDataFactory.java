package com.harrytmthy.data.movie.source;

import com.google.gson.GsonBuilder;

import com.harrytmthy.data.base.BaseEntityDataFactory;
import com.harrytmthy.data.common.DataConstants;

import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieEntityDataFactory, v 0.1 2019-12-11 11:23 by Harry Timothy
 */
@Singleton
public class MovieEntityDataFactory extends BaseEntityDataFactory {

    public MovieEntityData createService() {
        return new Retrofit.Builder().client(client)
            .baseUrl(DataConstants.URL_API)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
            .build().create(MovieEntityData.class);
    }

}
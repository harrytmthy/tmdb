package com.harrytmthy.data.authentication.source;

import com.google.gson.GsonBuilder;

import com.harrytmthy.data.base.BaseEntityDataFactory;
import com.harrytmthy.data.constants.DataConstants;

import javax.inject.Singleton;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthEntityDataFactory, v 0.1 2019-12-17 14:44 by Harry Timothy
 */
@Singleton
public class AuthEntityDataFactory extends BaseEntityDataFactory {

    public AuthEntityData createService() {
        return new Retrofit.Builder().client(client)
            .baseUrl(DataConstants.URL_API)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
            .build().create(AuthEntityData.class);
    }

}
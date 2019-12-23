package com.harrytmthy.data.base;

import com.google.gson.GsonBuilder;

import com.harrytmthy.data.constants.DataConstants;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version BaseEntityDataFactory, v 0.1 2019-12-10 16:09 by Harry Timothy
 */
public abstract class BaseEntityDataFactory {

    private static Interceptor authInterceptor = chain -> {
        HttpUrl newUrl = chain.request()
            .url().newBuilder()
            .addQueryParameter(DataConstants.PARAM_API_KEY, DataConstants.DEFAULT_API_KEY)
            .build();
        Request newRequest = chain.request().newBuilder().url(newUrl).build();
        return chain.proceed(newRequest);
    };

    private static OkHttpClient client = new OkHttpClient()
        .newBuilder()
        .addInterceptor(authInterceptor)
        .build();

    protected static Retrofit retrofit = new Retrofit.Builder().client(client)
        .baseUrl(DataConstants.URL_API)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
        .build();

}
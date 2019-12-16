package com.harrytmthy.data.base;

import com.harrytmthy.data.common.DataConstants;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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

    protected static OkHttpClient client = new OkHttpClient()
        .newBuilder()
        .addInterceptor(authInterceptor)
        .build();

}
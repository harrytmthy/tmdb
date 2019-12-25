package com.harrytmthy.data.base;

import com.google.gson.GsonBuilder;

import com.harrytmthy.data.R;
import com.harrytmthy.data.constants.DataConstants;

import android.content.Context;

import androidx.preference.PreferenceManager;
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

    private final Context context;

    public BaseEntityDataFactory(Context context) {
        this.context = context;
    }

    private String getSessionId() {
        return PreferenceManager.getDefaultSharedPreferences(context)
            .getString(context.getString(R.string.key_session_id), "");
    }

    private Interceptor getInterceptor() {
        return chain -> {
            final HttpUrl newUrl = chain.request()
                .url().newBuilder()
                .addQueryParameter(DataConstants.PARAM_API_KEY, DataConstants.DEFAULT_API_KEY)
                .addQueryParameter(DataConstants.PARAM_SESSION_ID, getSessionId())
                .build();
            final Request newRequest = chain.request().newBuilder().url(newUrl).build();
            return chain.proceed(newRequest);
        };
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient()
            .newBuilder()
            .addInterceptor(getInterceptor())
            .build();
    }

    protected Retrofit getClient() {
        return new Retrofit.Builder().client(getOkHttpClient())
            .baseUrl(DataConstants.URL_API)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
            .build();
    }

}
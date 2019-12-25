package com.harrytmthy.data.authentication.source;

import com.harrytmthy.data.base.BaseEntityDataFactory;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthEntityDataFactory, v 0.1 2019-12-17 14:44 by Harry Timothy
 */
@Singleton
public class AuthEntityDataFactory extends BaseEntityDataFactory {

    @Inject public AuthEntityDataFactory(Context context) {
        super(context);
    }

    public AuthEntityData createService() {
        return getClient().create(AuthEntityData.class);
    }

}
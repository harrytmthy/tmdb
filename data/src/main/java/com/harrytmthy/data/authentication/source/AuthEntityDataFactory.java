package com.harrytmthy.data.authentication.source;

import com.harrytmthy.data.base.BaseEntityDataFactory;

import javax.inject.Singleton;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthEntityDataFactory, v 0.1 2019-12-17 14:44 by Harry Timothy
 */
@Singleton
public class AuthEntityDataFactory extends BaseEntityDataFactory {

    public AuthEntityData createService() {
        return retrofit.create(AuthEntityData.class);
    }

}
package com.harrytmthy.data.account.source;

import com.harrytmthy.data.base.BaseEntityDataFactory;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AccountEntityDataFactory, v 0.1 2019-12-22 20:03 by Harry Timothy
 */
@Singleton
public class AccountEntityDataFactory extends BaseEntityDataFactory {

    @Inject public AccountEntityDataFactory(Context context) {
        super(context);
    }

    public AccountEntityData createService() {
        return getClient().create(AccountEntityData.class);
    }

}
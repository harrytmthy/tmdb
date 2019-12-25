package com.harrytmthy.data.source;

import com.harrytmthy.data.account.source.AccountEntityDataFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;
import retrofit2.HttpException;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AccountEntityDataFactoryTest, v 0.1 2019-12-22 23:25 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountEntityDataFactoryTest {

    private AccountEntityDataFactory accountEntityDataFactory;

    @Mock private Context context;

    @Before
    public void setUp() {
        accountEntityDataFactory = new AccountEntityDataFactory(context);
    }

    @Test
    public void createService_worksCorrectly() {
        final int page = 1;
        try {
            accountEntityDataFactory.createService().getFavoriteMovies(page);
        } catch (HttpException e) {
            assertEquals(401, e.code());
        }
    }

}
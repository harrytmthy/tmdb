package com.harrytmthy.data.source;

import com.harrytmthy.data.authentication.model.TokenResult;
import com.harrytmthy.data.authentication.source.AuthEntityDataFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;
import io.reactivex.Observable;

import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthEntityDataFactoryTest, v 0.1 2019-12-18 22:38 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthEntityDataFactoryTest {

    private AuthEntityDataFactory authEntityDataFactory;

    @Mock private Context context;

    @Mock private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        given(PreferenceManager.getDefaultSharedPreferences(context)).willReturn(sharedPreferences);
        authEntityDataFactory = new AuthEntityDataFactory(context);
    }

    @Test
    public void createService_worksCorrectly() {
        Observable<TokenResult> result = authEntityDataFactory.createService()
            .createToken();
        assertTrue(result.blockingFirst().getRequestToken().length() > 0);
    }

}

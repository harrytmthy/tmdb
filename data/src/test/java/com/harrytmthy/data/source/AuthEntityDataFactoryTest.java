package com.harrytmthy.data.source;

import com.harrytmthy.data.authentication.model.TokenResult;
import com.harrytmthy.data.authentication.source.AuthEntityDataFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.junit.Assert.assertTrue;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthEntityDataFactoryTest, v 0.1 2019-12-18 22:38 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthEntityDataFactoryTest {

    private AuthEntityDataFactory authEntityDataFactory;

    @Before
    public void setUp() {
        authEntityDataFactory = new AuthEntityDataFactory();
    }

    @Test
    public void createService_worksCorrectly() {
        Observable<TokenResult> result = authEntityDataFactory.createService()
            .createToken();
        assertTrue(result.blockingFirst().getRequestToken().length() > 0);
    }

}

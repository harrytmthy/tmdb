package com.harrytmthy.tmdb.mapper;

import com.harrytmthy.domain.authentication.model.Auth;
import com.harrytmthy.tmdb.authentication.mapper.AuthModelMapper;
import com.harrytmthy.tmdb.authentication.model.AuthState;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthModelMapperTest, v 0.1 2019-12-18 20:59 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthModelMapperTest {

    private AuthModelMapper authModelMapper;

    @Before
    public void setUp() {
        authModelMapper = new AuthModelMapper();
    }

    @Test
    public void mapToDataState_inAuthModelMapper_mapsCorrectly() {
        Auth auth = new Auth();
        auth.setRequestToken("01234");
        auth.setSessionId("56789");
        AuthState authState = authModelMapper.mapToDataState(auth);
        Auth data = ((AuthState.Data) authState).data;
        assertEquals(auth.getRequestToken(), data.getRequestToken());
        assertEquals(auth.getSessionId(), data.getSessionId());
    }

}
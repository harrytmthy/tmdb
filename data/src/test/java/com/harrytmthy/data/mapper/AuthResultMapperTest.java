package com.harrytmthy.data.mapper;

import com.harrytmthy.data.authentication.mapper.AuthResultMapper;
import com.harrytmthy.data.authentication.model.SessionResult;
import com.harrytmthy.data.authentication.model.TokenResult;
import com.harrytmthy.domain.authentication.model.Auth;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthResultMapperTest, v 0.1 2019-12-18 21:55 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthResultMapperTest {

    private AuthResultMapper authResultMapper;

    @Before
    public void setUp() {
        authResultMapper = new AuthResultMapper();
    }

    @Test
    public void mapTokenResult_worksCorrectly() {
        TokenResult result = new TokenResult();
        result.setRequestToken("rhu39erhq3rowia92EW");

        Auth auth = authResultMapper.map(result);

        assertEquals(result.getRequestToken(), auth.getRequestToken());
    }

    @Test
    public void mapTokenResult_returnsNull() {
        Auth auth = authResultMapper.map((TokenResult) null);
        assertNull(auth);
    }

    @Test
    public void mapSessionResult_worksCorrectly() {
        SessionResult result = new SessionResult();
        result.setSessionId("y73ruqoejrq3r");

        Auth auth = authResultMapper.map(result);

        assertEquals(result.getSessionId(), auth.getSessionId());
    }

    @Test
    public void mapSessionResult_returnsNull() {
        Auth auth = authResultMapper.map((SessionResult) null);
        assertNull(auth);
    }

}

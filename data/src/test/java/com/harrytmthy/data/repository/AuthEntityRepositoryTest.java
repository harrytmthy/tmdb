package com.harrytmthy.data.repository;

import com.harrytmthy.data.authentication.mapper.AuthResultMapper;
import com.harrytmthy.data.authentication.model.SessionResult;
import com.harrytmthy.data.authentication.model.TokenResult;
import com.harrytmthy.data.authentication.repository.AuthEntityRepository;
import com.harrytmthy.data.authentication.source.AuthEntityData;
import com.harrytmthy.data.authentication.source.AuthEntityDataFactory;
import com.harrytmthy.domain.authentication.model.SessionParam;
import com.harrytmthy.domain.authentication.model.TokenParam;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AuthEntityRepositoryTest, v 0.1 2019-12-17 16:14 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthEntityRepositoryTest {

    private AuthEntityRepository authEntityRepository;

    @Mock
    private AuthEntityDataFactory authEntityDataFactory;

    @Mock private AuthResultMapper authResultMapper;

    @Mock private AuthEntityData authEntityData;

    @Before
    public void setUp() {
        authEntityRepository = new AuthEntityRepository(authEntityDataFactory, authResultMapper);
        given(authEntityDataFactory.createService()).willReturn(authEntityData);
    }

    @Test
    public void createToken_inMovieEntityRepository_isCalled() {
        TokenResult tokenResult = new TokenResult();
        authResultMapper.map(tokenResult);
        given(authEntityData.createToken()).willReturn(Observable.just(tokenResult));

        authEntityRepository.createToken();

        verify(authEntityDataFactory).createService();
        verify(authEntityData).createToken();
    }

    @Test
    public void validateToken_inMovieEntityRepository_isCalled() {
        TokenResult tokenResult = new TokenResult();
        TokenParam tokenParam = new TokenParam();
        tokenParam.setUsername("harrytmthy");
        tokenParam.setPassword("test123");
        tokenParam.setRequestToken("j53rieuhwtr2q3u9jeo");
        given(authEntityData.validateToken(tokenParam)).willReturn(Observable.just(tokenResult));

        authEntityRepository.validateToken(tokenParam);

        verify(authEntityDataFactory).createService();
        verify(authEntityData).validateToken(tokenParam);
    }

    @Test
    public void createSession_inMovieEntityRepository_isCalled() {
        SessionResult sessionResult = new SessionResult();
        SessionParam sessionParam = new SessionParam();
        sessionParam.setRequestToken("4hi3rqr309irqo");
        given(authEntityData.createSession(sessionParam)).willReturn(Observable.just(sessionResult));

        authEntityRepository.createSession(sessionParam);

        verify(authEntityDataFactory).createService();
        verify(authEntityData).createSession(sessionParam);
    }

}
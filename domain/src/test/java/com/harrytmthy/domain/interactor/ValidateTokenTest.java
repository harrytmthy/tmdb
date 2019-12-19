package com.harrytmthy.domain.interactor;

import com.harrytmthy.domain.authentication.interactor.ValidateToken;
import com.harrytmthy.domain.authentication.model.TokenParam;
import com.harrytmthy.domain.authentication.repository.AuthRepository;
import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version ValidateTokenTest, v 0.1 2019-12-17 15:52 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidateTokenTest {

    private ValidateToken validateToken;

    @Mock
    private ThreadExecutor threadExecutor;

    @Mock private PostExecutionThread postExecutionThread;

    @Mock private AuthRepository authRepository;

    @Before
    public void setUp() {
        validateToken = new ValidateToken(authRepository, threadExecutor,
            postExecutionThread);
    }

    @Test
    public void validateToken_isCalled() {
        TokenParam param = new TokenParam();
        validateToken.buildUseCaseObservable(param);
        verify(authRepository).validateToken(param);
        verifyNoMoreInteractions(authRepository);
        verifyZeroInteractions(threadExecutor);
        verifyZeroInteractions(postExecutionThread);
    }

}
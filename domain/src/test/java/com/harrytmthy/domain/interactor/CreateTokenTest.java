package com.harrytmthy.domain.interactor;

import com.harrytmthy.domain.authentication.interactor.CreateToken;
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
 * @version CreateTokenTest, v 0.1 2019-12-17 15:41 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateTokenTest {

    private CreateToken createToken;

    @Mock private ThreadExecutor threadExecutor;

    @Mock private PostExecutionThread postExecutionThread;

    @Mock private AuthRepository authRepository;

    @Before
    public void setUp() {
        createToken = new CreateToken(authRepository, threadExecutor,
            postExecutionThread);
    }

    @Test
    public void createToken_isCalled() {
        createToken.buildUseCaseObservable(null);
        verify(authRepository).createToken();
        verifyNoMoreInteractions(authRepository);
        verifyZeroInteractions(threadExecutor);
        verifyZeroInteractions(postExecutionThread);
    }

}
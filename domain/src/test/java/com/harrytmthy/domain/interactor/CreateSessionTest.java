package com.harrytmthy.domain.interactor;

import com.harrytmthy.domain.authentication.interactor.CreateSession;
import com.harrytmthy.domain.authentication.model.SessionParam;
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
 * @version CreateSessionTest, v 0.1 2019-12-17 16:12 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class CreateSessionTest {

    private CreateSession createSession;

    @Mock private ThreadExecutor threadExecutor;

    @Mock private PostExecutionThread postExecutionThread;

    @Mock private AuthRepository authRepository;

    @Before
    public void setUp() {
        createSession = new CreateSession(authRepository, threadExecutor,
            postExecutionThread);
    }

    @Test
    public void createSession_isCalled() {
        SessionParam param = new SessionParam();
        createSession.buildUseCaseObservable(param);
        verify(authRepository).createSession(param);
        verifyNoMoreInteractions(authRepository);
        verifyZeroInteractions(threadExecutor);
        verifyZeroInteractions(postExecutionThread);
    }

}
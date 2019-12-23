package com.harrytmthy.domain.interactor;

import com.harrytmthy.domain.account.interactor.GetFavoriteMovies;
import com.harrytmthy.domain.account.repository.AccountRepository;
import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version GetFavoriteMoviesTest, v 0.1 2019-12-23 09:32 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class GetFavoriteMoviesTest {

    private GetFavoriteMovies getFavoriteMovies;

    @Mock private ThreadExecutor threadExecutor;

    @Mock private PostExecutionThread postExecutionThread;

    @Mock private AccountRepository accountRepository;

    @Before
    public void setUp() {
        getFavoriteMovies = new GetFavoriteMovies(accountRepository, threadExecutor,
            postExecutionThread);
    }

    @Test
    public void getPopularMovies_isCalled() {
        getFavoriteMovies.buildUseCaseObservable(1);
        verify(accountRepository).getFavoriteMovies(1);
        verifyZeroInteractions(threadExecutor);
        verifyZeroInteractions(postExecutionThread);
    }

}
package com.harrytmthy.domain.interactor;

import com.harrytmthy.domain.account.interactor.MarkFavorite;
import com.harrytmthy.domain.account.model.FavoriteParam;
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
 * @version MarkFavoriteTest, v 0.1 2019-12-23 09:34 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class MarkFavoriteTest {

    private MarkFavorite markFavorite;

    @Mock private ThreadExecutor threadExecutor;

    @Mock private PostExecutionThread postExecutionThread;

    @Mock private AccountRepository accountRepository;

    @Before
    public void setUp() {
        markFavorite = new MarkFavorite(accountRepository, threadExecutor, postExecutionThread);
    }

    @Test
    public void getPopularMovies_isCalled() {
        FavoriteParam favoriteParam = new FavoriteParam();
        markFavorite.buildUseCaseObservable(favoriteParam);
        verify(accountRepository).markFavorite(favoriteParam);
        verifyZeroInteractions(threadExecutor);
        verifyZeroInteractions(postExecutionThread);
    }

}
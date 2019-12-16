package com.harrytmthy.domain;

import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;
import com.harrytmthy.domain.movie.interactor.GetPopularMovies;
import com.harrytmthy.domain.movie.repository.MovieRepository;

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
 * @version GetPopularMoviesTest, v 0.1 2019-12-11 17:54 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class GetPopularMoviesTest {

    private GetPopularMovies getPopularMovies;

    @Mock private ThreadExecutor threadExecutor;

    @Mock private PostExecutionThread postExecutionThread;

    @Mock private MovieRepository movieRepository;

    @Before
    public void setUp() {
        getPopularMovies = new GetPopularMovies(movieRepository, threadExecutor,
            postExecutionThread);
    }

    @Test
    public void getPopularMovies_isCalled() {
        getPopularMovies.buildUseCaseObservable(null);
        verify(movieRepository).getPopularMovie();
        verifyNoMoreInteractions(movieRepository);
        verifyZeroInteractions(threadExecutor);
        verifyZeroInteractions(postExecutionThread);
    }

}

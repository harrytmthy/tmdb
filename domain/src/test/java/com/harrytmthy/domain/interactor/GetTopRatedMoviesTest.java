package com.harrytmthy.domain.interactor;

import com.harrytmthy.domain.executor.PostExecutionThread;
import com.harrytmthy.domain.executor.ThreadExecutor;
import com.harrytmthy.domain.movie.interactor.GetTopRatedMovies;
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
public class GetTopRatedMoviesTest {

    private GetTopRatedMovies getTopRatedMovies;

    @Mock private ThreadExecutor threadExecutor;

    @Mock private PostExecutionThread postExecutionThread;

    @Mock private MovieRepository movieRepository;

    @Before
    public void setUp() {
        getTopRatedMovies = new GetTopRatedMovies(movieRepository, threadExecutor,
            postExecutionThread);
    }

    @Test
    public void getTopRatedMovies_isCalled() {
        getTopRatedMovies.buildUseCaseObservable(1);
        verify(movieRepository).getTopRatedMovie(1);
        verifyNoMoreInteractions(movieRepository);
        verifyZeroInteractions(threadExecutor);
        verifyZeroInteractions(postExecutionThread);
    }

}

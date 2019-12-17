package com.harrytmthy.tmdb;

import com.harrytmthy.domain.movie.interactor.GetPopularMovies;
import com.harrytmthy.tmdb.movie.MoviePresenter;
import com.harrytmthy.tmdb.movie.MovieView;
import com.harrytmthy.tmdb.movie.mapper.MovieModelMapper;
import com.harrytmthy.tmdb.movie.model.MovieAction;
import com.harrytmthy.tmdb.movie.model.MovieState;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MoviePresenterTest, v 0.1 2019-12-12 14:21 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class MoviePresenterTest {

    private MoviePresenter moviePresenter;

    @Mock private MovieView<MovieState> movieView;

    @Mock private GetPopularMovies getPopularMovies;

    @Mock private MovieModelMapper movieModelMapper;

    @Before
    public void setUp() {
        moviePresenter = new MoviePresenter(getPopularMovies, movieModelMapper);
    }

    @Test
    public void movieState_inMoviePresenter_isRendered() {
        MovieAction action = new MovieAction.Initial();
        MovieState state = new MovieState.Loading();

        given(movieModelMapper.toLoadingState()).willReturn(state);

        moviePresenter.bind(movieView);
        moviePresenter.doAction(action);

        verify(movieModelMapper).toLoadingState();
        verify(movieView).render(state);
    }

}
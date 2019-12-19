package com.harrytmthy.tmdb.presenter;

import com.harrytmthy.domain.movie.interactor.GetPopularMovies;
import com.harrytmthy.domain.movie.interactor.GetTopRatedMovies;
import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.tmdb.movie.MoviePresenter;
import com.harrytmthy.tmdb.movie.MovieView;
import com.harrytmthy.tmdb.movie.mapper.MovieModelMapper;
import com.harrytmthy.tmdb.movie.model.MovieAction;
import com.harrytmthy.tmdb.movie.model.MovieState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MoviePresenterTest, v 0.1 2019-12-12 14:21 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class MoviePresenterTest {

    private MoviePresenter moviePresenter;

    @Mock private MovieView<MovieState> movieView;

    @Mock private GetPopularMovies getPopularMovies;

    @Mock private GetTopRatedMovies getTopRatedMovies;

    @Mock private MovieModelMapper movieModelMapper;

    @Before
    public void setUp() {
        moviePresenter = new MoviePresenter(getPopularMovies, getTopRatedMovies, movieModelMapper);
        moviePresenter.bind(movieView);
    }

    @After
    public void tearDown() {
        moviePresenter.unbind();
    }

    @Test
    public void movieState_inMoviePresenter_isRendered() {
        MovieAction action = new MovieAction.Initial();
        MovieState state = new MovieState.Loading();

        given(movieModelMapper.toLoadingState()).willReturn(state);

        moviePresenter.doAction(action);

        verify(movieModelMapper).toLoadingState();
        verify(movieView).render(state);
    }

    @Test
    public void useCase_inMoviePresenter_isExecuted() {
        PagedMovie pagedMovie = new PagedMovie();
        MovieAction action = new MovieAction.LoadPopularMovies(1);

        given(getPopularMovies.execute(1)).willReturn(Observable.just(pagedMovie));

        moviePresenter.doAction(action);

        verify(getPopularMovies).execute(1);
        verifyZeroInteractions(getTopRatedMovies);
    }

    @Test
    public void canLoadNextPage_inMoviePresenter_worksCorrectly() {
        moviePresenter.canLoadNextPage = false;
        moviePresenter.canLoadNextPage();
        assertTrue(moviePresenter.canLoadNextPage);
    }

    @Test
    public void nextPage_worksCorrectly() {
        moviePresenter.canLoadNextPage = true;
        moviePresenter.doAction(new MovieAction.LoadPopularMovies(1));
        moviePresenter.nextPage();
        moviePresenter.doAction(new MovieAction.LoadTopRatedMovies(1));
        moviePresenter.nextPage();
        assertFalse(moviePresenter.canLoadNextPage);
    }

    @Test
    public void setDefaultAction_worksCorrectly() {
        MovieAction action = new MovieAction.Initial();
        moviePresenter.setDefaultAction(action);
        assertEquals(action, moviePresenter.getDefaultAction());
    }

}
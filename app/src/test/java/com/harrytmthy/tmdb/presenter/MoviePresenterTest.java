package com.harrytmthy.tmdb.presenter;

import com.harrytmthy.domain.account.interactor.GetFavoriteMovies;
import com.harrytmthy.domain.movie.interactor.GetPopularMovies;
import com.harrytmthy.domain.movie.interactor.GetTopRatedMovies;
import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.tmdb.movie.list.MoviePresenter;
import com.harrytmthy.tmdb.movie.list.MovieView;
import com.harrytmthy.tmdb.movie.list.mapper.MovieModelMapper;
import com.harrytmthy.tmdb.movie.list.model.MovieAction;
import com.harrytmthy.tmdb.movie.list.model.MovieState;

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

    @Mock private GetFavoriteMovies getFavoriteMovies;

    @Mock private MovieModelMapper movieModelMapper;

    @Before
    public void setUp() {
        moviePresenter = new MoviePresenter(getPopularMovies, getTopRatedMovies, getFavoriteMovies,
            movieModelMapper);
        moviePresenter.bind(movieView);
    }

    @After
    public void tearDown() {
        moviePresenter.unbind();
    }

    @Test
    public void useCase_inMoviePresenter_isExecuted() {
        PagedMovie pagedMovie = new PagedMovie();

        given(getPopularMovies.execute(1)).willReturn(Observable.just(pagedMovie));
        given(getTopRatedMovies.execute(1)).willReturn(Observable.just(pagedMovie));

        MovieAction action = new MovieAction.LoadPopularMovies();
        moviePresenter.doAction(action);

        action = new MovieAction.LoadTopRatedMovies();
        moviePresenter.doAction(action);

        action = new MovieAction.LoadFavoriteMovies();
        moviePresenter.doAction(action);

        verify(getPopularMovies).execute(1);
        verify(getTopRatedMovies).execute(1);
        verify(getFavoriteMovies).execute(1);
    }

    @Test
    public void setCanLoadNextPage_inMoviePresenter_worksCorrectly() {
        moviePresenter.canLoadNextPage = false;
        moviePresenter.setCanLoadNextPage(true);
        assertTrue(moviePresenter.canLoadNextPage);
    }

    @Test
    public void loadNextPage_worksCorrectly() {
        moviePresenter.lastUseCase = getTopRatedMovies;

        moviePresenter.loadNextPage();
        verifyZeroInteractions(getTopRatedMovies);

        moviePresenter.canLoadNextPage = true;
        moviePresenter.loadNextPage();

        verify(getTopRatedMovies).execute(2);
        assertFalse(moviePresenter.canLoadNextPage);
    }

    @Test
    public void refresh_refreshActionIsHandled() {
        moviePresenter.lastUseCase = getPopularMovies;

        given(getPopularMovies.execute(1)).willReturn(Observable.just(new PagedMovie()));
        moviePresenter.refresh();

        verify(getPopularMovies).execute(1);
    }

    @Test
    public void setCurrentPage_worksCorrectly() {
        final int currentPage = 10;
        moviePresenter.setCurrentPage(currentPage);
        assertEquals(10, moviePresenter.currentPage);
    }

}
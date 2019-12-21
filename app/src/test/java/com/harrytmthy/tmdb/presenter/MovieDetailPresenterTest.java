package com.harrytmthy.tmdb.presenter;

import com.harrytmthy.domain.movie.interactor.GetDetails;
import com.harrytmthy.domain.movie.model.MovieDetail;
import com.harrytmthy.tmdb.movie.detail.MovieDetailPresenter;
import com.harrytmthy.tmdb.movie.detail.MovieDetailView;
import com.harrytmthy.tmdb.movie.detail.mapper.MovieDetailModelMapper;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailAction;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailState;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetailPresenterTest, v 0.1 2019-12-20 09:55 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieDetailPresenterTest {

    private MovieDetailPresenter movieDetailPresenter;

    @Mock private MovieDetailView<MovieDetailState> movieDetailView;

    @Mock private GetDetails getDetails;

    @Mock private MovieDetailModelMapper movieDetailModelMapper;

    @Before
    public void setUp() {
        movieDetailPresenter = new MovieDetailPresenter(getDetails, movieDetailModelMapper);
        movieDetailPresenter.bind(movieDetailView);
    }

    @After
    public void tearDown() {
        movieDetailPresenter.unbind();
    }

    @Test
    public void useCase_inMovieDetailPresenter_isExecuted() {
        MovieDetail movieDetail = new MovieDetail();
        MovieDetailAction action = new MovieDetailAction.LoadDetails(1);

        given(getDetails.execute(1)).willReturn(Observable.just(movieDetail));

        movieDetailPresenter.doAction(action);

        verify(getDetails).execute(1);
    }

}
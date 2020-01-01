package com.harrytmthy.tmdb.presenter;

import com.harrytmthy.domain.account.interactor.MarkFavorite;
import com.harrytmthy.domain.movie.interactor.GetDetails;
import com.harrytmthy.domain.movie.model.MovieDetail;
import com.harrytmthy.tmdb.movie.detail.MovieDetailContract;
import com.harrytmthy.tmdb.movie.detail.MovieDetailPresenter;
import com.harrytmthy.tmdb.movie.detail.mapper.MovieDetailModelMapper;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailAction;

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

    @Mock private MovieDetailContract.View movieDetailView;

    @Mock private GetDetails getDetails;

    @Mock private MarkFavorite markFavorite;

    @Mock private MovieDetailModelMapper movieDetailModelMapper;

    @Before
    public void setUp() {
        movieDetailPresenter = new MovieDetailPresenter(getDetails, markFavorite,
            movieDetailModelMapper);
        movieDetailPresenter.bind(movieDetailView);
    }

    @After
    public void tearDown() {
        movieDetailPresenter.unbind();
    }

    @Test
    public void useCase_inMovieDetailPresenter_isExecuted() {
        MovieDetail movieDetail = new MovieDetail();
        MovieDetailAction action = new MovieDetailAction.LoadDetails();
        movieDetailPresenter.setMovieId(1);

        given(getDetails.execute(1)).willReturn(Observable.just(movieDetail));

        movieDetailPresenter.doAction(action);

        movieDetailPresenter.markFavorite(true);

        movieDetailPresenter.markFavorite(false);

        verify(getDetails).execute(1);
    }

}
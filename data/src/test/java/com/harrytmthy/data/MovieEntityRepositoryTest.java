package com.harrytmthy.data;

import com.harrytmthy.data.common.PagedResult;
import com.harrytmthy.data.movie.mapper.MovieResultMapper;
import com.harrytmthy.data.movie.model.MovieResult;
import com.harrytmthy.data.movie.repository.MovieEntityRepository;
import com.harrytmthy.data.movie.source.MovieEntityData;
import com.harrytmthy.data.movie.source.MovieEntityDataFactory;

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
 * @version MovieEntityRepositoryTest, v 0.1 2019-12-11 18:40 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieEntityRepositoryTest {

    private MovieEntityRepository movieEntityRepository;

    @Mock private MovieEntityDataFactory movieEntityDataFactory;

    @Mock private MovieResultMapper movieResultMapper;

    @Mock private MovieEntityData movieEntityData;

    @Before
    public void setUp() {
        movieEntityRepository = new MovieEntityRepository(movieEntityDataFactory, movieResultMapper);
        given(movieEntityDataFactory.createService()).willReturn(movieEntityData);
    }

    @Test
    public void getPopularMovie_inMovieEntityRepository_isCalled() {
        PagedResult<MovieResult> pagedMovieResult = new PagedResult<>();
        movieResultMapper.map(pagedMovieResult);
        given(movieEntityData.getPopularMovie(1)).willReturn(Observable.just(pagedMovieResult));

        movieEntityRepository.getPopularMovie(1);

        verify(movieEntityDataFactory).createService();
        verify(movieEntityData).getPopularMovie(1);
    }

}

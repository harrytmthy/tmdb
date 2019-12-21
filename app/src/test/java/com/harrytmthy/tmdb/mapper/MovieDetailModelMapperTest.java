package com.harrytmthy.tmdb.mapper;

import com.harrytmthy.domain.movie.model.MovieDetail;
import com.harrytmthy.tmdb.movie.detail.mapper.MovieDetailModelMapper;
import com.harrytmthy.tmdb.movie.detail.model.MovieDetailState;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieDetailModelMapperTest, v 0.1 2019-12-20 10:24 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieDetailModelMapperTest {

    private MovieDetailModelMapper movieDetailModelMapper;

    @Before
    public void setUp() {
        movieDetailModelMapper = new MovieDetailModelMapper();
    }

    @Test
    public void mapToDataState_inMovieDetailModelMapper_mapsCorrectly() {
        MovieDetail movieDetail = new MovieDetail();
        movieDetail.setId(1);
        movieDetail.setOriginalTitle("Test Movie");

        MovieDetailState state = movieDetailModelMapper.mapToDataState(movieDetail);
        MovieDetail data = ((MovieDetailState.Data) state).data;

        assertEquals(movieDetail.getId(), data.getId());
        assertEquals(movieDetail.getOriginalTitle(), data.getOriginalTitle());
    }

}
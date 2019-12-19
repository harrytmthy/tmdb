package com.harrytmthy.tmdb.mapper;

import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.tmdb.movie.mapper.MovieModelMapper;
import com.harrytmthy.tmdb.movie.model.MovieState;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieModelMapperTest, v 0.1 2019-12-18 20:45 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieModelMapperTest {

    private MovieModelMapper movieModelMapper;

    @Before
    public void setUp() {
        movieModelMapper = new MovieModelMapper();
    }

    @Test
    public void mapToDataState_inMovieModelMapper_mapsCorrectly() {
        PagedMovie pagedMovie = new PagedMovie();
        pagedMovie.setPage(1);
        pagedMovie.setTotalPages(12);

        MovieState movieState = movieModelMapper.mapToDataState(pagedMovie);
        PagedMovie data = ((MovieState.Data) movieState).data;

        assertEquals(pagedMovie.getPage(), data.getPage());
        assertEquals(pagedMovie.getTotalPages(), data.getTotalPages());
    }

    @Test
    public void toLoadingState_inMovieModelMapper_mapsCorrectly() {
        MovieState state = movieModelMapper.toLoadingState();
        assertTrue(state instanceof MovieState.Loading);
    }

}
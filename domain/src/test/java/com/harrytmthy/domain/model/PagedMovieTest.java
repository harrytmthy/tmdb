package com.harrytmthy.domain.model;

import com.harrytmthy.domain.movie.model.PagedMovie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version PagedMovieTest, v 0.1 2019-12-18 21:46 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class PagedMovieTest {

    private PagedMovie pagedMovie;

    @Before
    public void setUp() {
        pagedMovie = new PagedMovie();
    }

    @Test
    public void movie_worksCorrectly() {
        int page = 2;
        pagedMovie.setPage(2);
        assertEquals(page, pagedMovie.getPage());
    }

}

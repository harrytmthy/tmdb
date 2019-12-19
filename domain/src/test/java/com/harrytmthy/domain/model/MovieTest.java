package com.harrytmthy.domain.model;

import com.harrytmthy.domain.movie.model.Movie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieTest, v 0.1 2019-12-18 21:41 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieTest {

    private Movie movie;

    @Before
    public void setUp() {
        movie = new Movie();
    }

    @Test
    public void movie_worksCorrectly() {
        int id = 123;
        movie.setId(id);
        assertEquals(id, movie.getId());
    }

}

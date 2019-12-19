package com.harrytmthy.data.source;

import com.harrytmthy.data.common.PagedResult;
import com.harrytmthy.data.movie.model.MovieResult;
import com.harrytmthy.data.movie.source.MovieEntityDataFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieEntityDataFactoryTest, v 0.1 2019-12-18 22:10 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieEntityDataFactoryTest {

    private MovieEntityDataFactory movieEntityDataFactory;

    @Before
    public void setUp() {
        movieEntityDataFactory = new MovieEntityDataFactory();
    }

    @Test
    public void createService_worksCorrectly() {
        final int page = 1;
        Observable<PagedResult<MovieResult>> result = movieEntityDataFactory.createService()
            .getPopularMovie(page);
        assertEquals(page, result.blockingFirst().getPage());
    }

}
package com.harrytmthy.data.source;

import com.harrytmthy.data.common.PagedResult;
import com.harrytmthy.data.movie.model.MovieResult;
import com.harrytmthy.data.movie.source.MovieEntityDataFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;
import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieEntityDataFactoryTest, v 0.1 2019-12-18 22:10 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieEntityDataFactoryTest {

    private MovieEntityDataFactory movieEntityDataFactory;

    @Mock private Context context;

    @Mock private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        given(PreferenceManager.getDefaultSharedPreferences(context)).willReturn(sharedPreferences);
        movieEntityDataFactory = new MovieEntityDataFactory(context);
    }

    @Test
    public void createService_worksCorrectly() {
        final int page = 1;
        Observable<PagedResult<MovieResult>> result = movieEntityDataFactory.createService()
            .getPopularMovie(page);
        assertEquals(page, result.blockingFirst().getPage());
    }

}
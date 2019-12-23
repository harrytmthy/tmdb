package com.harrytmthy.data.mapper;

import com.harrytmthy.data.account.mapper.AccountResultMapper;
import com.harrytmthy.data.account.model.StatusResult;
import com.harrytmthy.data.common.PagedResult;
import com.harrytmthy.data.movie.model.MovieResult;
import com.harrytmthy.domain.account.model.Status;
import com.harrytmthy.domain.movie.model.PagedMovie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version AccountResultMapperTest, v 0.1 2019-12-22 23:05 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountResultMapperTest {

    private AccountResultMapper accountResultMapper;

    @Before
    public void setUp() {
        accountResultMapper = new AccountResultMapper();
    }

    @Test
    public void mapStatusResult_worksCorrectly() {
        StatusResult result = new StatusResult();
        result.setStatusCode(200);
        result.setStatusMessge("success");

        Status status = accountResultMapper.map(result);

        assertEquals(result.getStatusCode(), status.getCode());
    }

    @Test
    public void mapTokenResult_returnsNull() {
        Status status = accountResultMapper.map((StatusResult) null);
        assertNull(status);
    }

    @Test
    public void mapToPagedMovie_worksCorrectly() {
        MovieResult movieResult = new MovieResult();
        movieResult.setId(738);
        List<MovieResult> movieResults = new ArrayList<>();
        movieResults.add(movieResult);
        PagedResult<MovieResult> pagedResult = new PagedResult<>();
        pagedResult.setResults(movieResults);

        PagedMovie pagedMovie = accountResultMapper.map(pagedResult);

        assertEquals(movieResult.getId(), pagedMovie.getMovies().get(0).getId());
    }

    @Test
    public void mapToPagedMovie_returnsNull() {
        PagedMovie pagedMovie = accountResultMapper.map((PagedResult<MovieResult>) null);
        assertNull(pagedMovie);
    }

}
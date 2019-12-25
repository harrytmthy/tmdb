package com.harrytmthy.data.mapper;

import com.harrytmthy.data.common.PagedResult;
import com.harrytmthy.data.movie.mapper.MovieResultMapper;
import com.harrytmthy.data.movie.model.MovieResult;
import com.harrytmthy.domain.movie.model.AccountState;
import com.harrytmthy.domain.movie.model.Genre;
import com.harrytmthy.domain.movie.model.MovieDetail;
import com.harrytmthy.domain.movie.model.PagedMovie;
import com.harrytmthy.domain.movie.model.Video;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version MovieResultMapperTest, v 0.1 2019-12-18 22:00 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieResultMapperTest {

    private MovieResultMapper movieResultMapper;

    @Before
    public void setUp() {
        movieResultMapper = new MovieResultMapper();
    }

    @Test
    public void mapToPagedMovie_worksCorrectly() {
        MovieResult movieResult = new MovieResult();
        movieResult.setId(738);
        List<MovieResult> movieResults = new ArrayList<>();
        movieResults.add(movieResult);
        PagedResult<MovieResult> pagedResult = new PagedResult<>();
        pagedResult.setResults(movieResults);
        pagedResult.setPage(1);
        pagedResult.setTotalPages(12);

        PagedMovie pagedMovie = movieResultMapper.map(pagedResult);

        assertEquals(movieResult.getId(), pagedMovie.getMovies().get(0).getId());
    }

    @Test
    public void mapToPagedMovie_returnsNull() {
        PagedMovie pagedMovie = movieResultMapper.map((PagedResult<MovieResult>) null);
        assertNull(pagedMovie);
    }

    @Test
    public void mapToMovieDetail_worksCorrectly() {
        Video video = new Video();
        video.setKey("key2ijeor3q");
        List<Video> videos = new ArrayList<>();
        videos.add(video);
        Map<Object, List<Video>> videoMap = new HashMap<>();
        videoMap.put(new Object(), videos);
        Genre genre = new Genre();
        genre.setName("Test123");
        List<Genre> genres = new ArrayList<>();
        genres.add(genre);
        AccountState accountState = new AccountState();
        accountState.setFavorite(true);
        MovieResult movieResult = new MovieResult();
        movieResult.setGenres(genres);

        MovieDetail movieDetail = movieResultMapper.map(movieResult);

        assertNull(movieDetail.getVideos());

        movieResult.setVideos(videoMap);

        movieDetail = movieResultMapper.map(movieResult);

        assertFalse(movieDetail.isFavorite());

        movieResult.setAccountState(accountState);

        movieDetail = movieResultMapper.map(movieResult);

        assertEquals(genre.getName(), movieDetail.getGenres().get(0));
        assertEquals(video.getKey(), movieDetail.getVideos().get(0).getKey());
    }

    @Test
    public void mapToMovieDetail_returnsNull() {
        MovieDetail movieDetail = movieResultMapper.map((MovieResult) null);
        assertNull(movieDetail);
    }

}
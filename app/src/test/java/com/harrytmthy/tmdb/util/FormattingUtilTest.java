package com.harrytmthy.tmdb.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.harrytmthy.tmdb.util.FormattingUtil.formatGenre;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version FormattingUtilTest, v 0.1 2019-12-21 21:31 by Harry Timothy
 */
@RunWith(MockitoJUnitRunner.class)
public class FormattingUtilTest {

    @Test
    public void formatGenre_returnsCorrectString() {
        final String expectedGenreFormat = "Mystery, Drama, Action, Thriller";
        List<String> genres = new ArrayList<>();
        assertTrue(formatGenre(genres).isEmpty());

        genres.add("Mystery");
        genres.add("Drama");
        genres.add("Action");
        genres.add("Thriller");
        assertEquals(expectedGenreFormat, formatGenre(genres));
    }

}
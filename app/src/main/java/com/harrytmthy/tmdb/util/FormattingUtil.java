package com.harrytmthy.tmdb.util;

import java.util.List;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version FormattingUtil, v 0.1 2019-12-20 21:16 by Harry Timothy
 */
public final class FormattingUtil {

    public static String formatGenre(List<String> genres) {
        if(genres == null || genres.isEmpty()) return "";
        String genreText = "";
        for(String genre : genres) {
            genreText = genreText.concat(genre + ", ");
        }
        return genreText.substring(0, genreText.length() - 2);
    }

}
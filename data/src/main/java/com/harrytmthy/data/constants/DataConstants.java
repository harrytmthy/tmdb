package com.harrytmthy.data.constants;

/**
 * @author Harry Timothy (harry.timothy@dana.id)
 * @version DataConstants, v 0.1 2019-12-10 18:04 by Harry Timothy
 */
public final class DataConstants {

    public static final String API_MARK_FAVORITE = "/3/account/{account_id}/favorite";

    public static final String API_MOVIE_FAVORITE = "/3/account/{account_id}/favorite/movies";

    public static final String API_MOVIE_POPULAR = "/3/movie/popular";

    public static final String API_MOVIE_TOP_RATED = "/3/movie/top_rated";

    public static final String API_MOVIE_DETAIL = "/3/movie/{movie_id}";

    public static final String API_NEW_TOKEN = "/3/authentication/token/new";

    public static final String API_NEW_SESSION = "/3/authentication/session/new";

    public static final String API_VALIDATE_TOKEN = "/3/authentication/token/validate_with_login";

    public static final String DEFAULT_API_KEY = "e4a4544757cebf9ce932d484e99bdefd";

    public static final String DEFAULT_APPEND_RESPONSE = "account_states,videos";

    public static final String DEFAULT_MEDIA_TYPE = "movie";

    public static final String PARAM_API_KEY = "api_key";

    public static final String PARAM_APPEND_RESPONSE = "append_to_response";

    public static final String PARAM_MOVIE_ID = "movie_id";

    public static final String PARAM_PAGE = "page";

    public static final String PARAM_SESSION_ID = "session_id";

    public static final String URL_API = "https://api.themoviedb.org";

    public static final String URL_IMAGE = "https://image.tmdb.org/t/p/w500";

    public static final String URL_REGISTER = "https://www.themoviedb.org/account/signup";

}
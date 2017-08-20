package com.mhmt.movies.helper;

import com.mhmt.movies.BuildConfig;

public class ApiUrl {

  private final static String BASE_URL = "http://www.omdbapi.com";
  private final static String SEARCH_FIELD = "s=";
  private final static String IMDB_ID_FIELD = "i=";
  private final static String API_FIELD = "apikey=";
  private final static String PARAMETER_BEGINNING = "?";
  private final static String SLASH_SEPARATOR = "/";
  private final static String PARAMETER_SEPARATOR = "&";

  public static String getSearchURL(final String titleQuery) {
    return BASE_URL.concat(SLASH_SEPARATOR)
                   .concat(PARAMETER_BEGINNING)
                   .concat(SEARCH_FIELD).concat(titleQuery.trim().replaceAll("\\s", "+"))
                   .concat(PARAMETER_SEPARATOR).concat(API_FIELD)
                   .concat(BuildConfig.API_KEY);
  }

  public static String getMovieURL(final String imdbID) {
    return BASE_URL.concat(SLASH_SEPARATOR)
                   .concat(PARAMETER_BEGINNING)
                   .concat(IMDB_ID_FIELD).concat(imdbID.trim().replaceAll("\\s", "+"))
                   .concat(PARAMETER_SEPARATOR).concat(API_FIELD)
                   .concat(BuildConfig.API_KEY);
  }

}

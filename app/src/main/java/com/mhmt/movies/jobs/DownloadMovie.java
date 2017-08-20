package com.mhmt.movies.jobs;

import com.mhmt.movies.domain.Movie;
import com.mhmt.movies.helper.ApiUrl;

import java.io.IOException;
import java.net.URL;

public class DownloadMovie extends BaseNetworkJob<Movie, String, Void, Movie>{

  public DownloadMovie(DownloadCallback<Movie> callback) {
    super(callback);
  }

  @Override protected void onPreExecute() {

  }

  @Override protected void onPostExecute(final Movie movie) {
    if (movie != null && callback != null) {
        callback.dataFromDownload(movie);
      callback.finishDownloading();
    }

  }

  @Override protected Movie doInBackground(final String... params) {
    Movie movie = null;
    if (!isCancelled() && params != null && params.length > 0) {
      try {
        URL url = new URL(ApiUrl.getMovieURL(params[0]));
        String resultString = fetch(url);
        if (resultString != null) {
          movie = mapper.readValue(resultString, Movie.class);
        } else {
          throw new IOException("No response received.");
        }
      } catch(Exception e) {
        movie = new Movie();
      }
    }
    return movie;
  }
}

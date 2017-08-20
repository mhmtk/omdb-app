package com.mhmt.movies.jobs;

import com.mhmt.movies.domain.Movie;
import com.mhmt.movies.domain.SearchResponse;
import com.mhmt.movies.helper.ApiUrl;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class DownloadMovies extends BaseNetworkJob<List<Movie>, String, Void, SearchResponse> {

  public DownloadMovies(final DownloadCallback<List<Movie>> callback) {
    super(callback);
  }

  @Override protected void onPreExecute() {

  }

  @Override protected void onPostExecute(final SearchResponse searchResponse) {
    if (searchResponse != null && callback != null) {
      if (!searchResponse.isResponse()) {
        callback.errorFromDownload(ResponseError.MOVIES);
      } else {
        callback.dataFromDownload(searchResponse.getSearch());
      }
      callback.finishDownloading();
    }
  }

  @Override protected SearchResponse doInBackground(final String... params) {
    SearchResponse result = null;
    if (!isCancelled() && params != null && params.length > 0) {
      try {
        URL url = new URL(ApiUrl.getSearchURL(params[0]));
        String resultString = fetch(url);
        if (resultString != null) {
          result = mapper.readValue(resultString, SearchResponse.class);
        } else {
          throw new IOException("No response received.");
        }
      } catch(Exception e) {
        result = new SearchResponse();
      }
    }
    return result;
  }

}

package com.mhmt.movies.jobs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mhmt.movies.domain.DownloadPosterData;

import java.io.InputStream;
import java.net.URL;

public class DownloadPoster extends BaseNetworkJob<DownloadPosterData, String, Void, DownloadPosterData>{

  private final String movieImdbId;

  public DownloadPoster(final DownloadCallback<DownloadPosterData> callback, final String movieImdbId) {
    super(callback);
    this.movieImdbId = movieImdbId;
  }

  @Override protected void onPreExecute() {

  }

  @Override protected void onPostExecute(final DownloadPosterData data) {
    if (data != null && callback != null) {
      if (!data.isSuccess()) {
        callback.errorFromDownload(ResponseError.POSTER);
      } else {
        callback.dataFromDownload(data);
      }
      callback.finishDownloading();
    }

  }

  @Override protected DownloadPosterData doInBackground(final String... params) {
    DownloadPosterData data = null;
    if (!isCancelled() && params != null && params.length > 0) {
      try {
        InputStream in = new URL(params[0]).openStream();
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        data = new DownloadPosterData(true, movieImdbId, bitmap);
      } catch(Exception e) {
        data = new DownloadPosterData(false, null, null);
      }
    }
    return data;
  }

}

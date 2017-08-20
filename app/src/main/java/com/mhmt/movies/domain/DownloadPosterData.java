package com.mhmt.movies.domain;

import android.graphics.Bitmap;

public class DownloadPosterData {

  private boolean success;
  private String movieImdbId;
  private Bitmap posterBitmap;

  public DownloadPosterData(final boolean success, final String movieImdbId, final Bitmap posterBitmap) {
    this.success = success;
    this.movieImdbId = movieImdbId;
    this.posterBitmap = posterBitmap;
  }

  public String getMovieImdbId() {
    return movieImdbId;
  }

  public void setMovieImdbId(final String movieImdbId) {
    this.movieImdbId = movieImdbId;
  }

  public Bitmap getPosterBitmap() {
    return posterBitmap;
  }

  public void setPosterBitmap(final Bitmap posterBitmap) {
    this.posterBitmap = posterBitmap;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(final boolean success) {
    this.success = success;
  }
}

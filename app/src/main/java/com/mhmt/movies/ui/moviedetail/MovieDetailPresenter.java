package com.mhmt.movies.ui.moviedetail;

import android.os.AsyncTask;

import com.mhmt.movies.helper.PosterLruCache;
import com.mhmt.movies.domain.DownloadPosterData;
import com.mhmt.movies.domain.Movie;
import com.mhmt.movies.jobs.DownloadCallback;
import com.mhmt.movies.jobs.DownloadMovie;
import com.mhmt.movies.jobs.DownloadPoster;
import com.mhmt.movies.jobs.ResponseError;
import com.mhmt.movies.ui.base.BasePresenter;

public class MovieDetailPresenter extends BasePresenter<MovieDetailMvpView> implements MovieDetailMvpPresenter<MovieDetailMvpView> {

  private final String imdbID;
  private AsyncTask<String, Void, Movie> downloadTask;
  private PosterLruCache posterCache;
  private AsyncTask<String, Void, DownloadPosterData> posterTask;

  public MovieDetailPresenter(final String imdbID) {
    this.imdbID = imdbID;
  }

  @Override public void onCreate(final MovieDetailMvpView mvpView) {
    super.onCreate(mvpView);
    PosterCallback posterCallback = new PosterCallback();
    MovieCallback movieCallback = new MovieCallback();

    downloadTask = new DownloadMovie(movieCallback).execute(imdbID);

    posterCache = PosterLruCache.getInstance();
    if (posterCache.contains(imdbID)) {
      getMvpView().showPoster(posterCache.getBitmapFromMemCache(imdbID));
    } else {
      posterTask = new DownloadPoster(posterCallback, imdbID).execute();
    }
  }

  @Override public void onResume() {

  }

  @Override public void onPause() {

  }

  @Override public void onDestroy() {
    if (downloadTask != null) {
      downloadTask.cancel(true);
    }
    if (posterTask != null) {
      posterTask.cancel(true);
    }
  }

  public void downloadError(final ResponseError error) {
  }

  public void movieDownloaded(final Movie movie) {
    getMvpView().updateData(movie);
  }

  public void posterDownloaded(final DownloadPosterData posterData) {
    posterCache.addBitmapToMemoryCache(imdbID, posterData.getPosterBitmap());
    getMvpView().showPoster(posterData.getPosterBitmap());
  }

  public void downloadFinished() {
  }

  private class MovieCallback implements DownloadCallback<Movie> {

    @Override public void errorFromDownload(final ResponseError error) {
      downloadError(error);
    }

    @Override public void dataFromDownload(final Movie movies) {
      movieDownloaded(movies);
    }

    @Override public void finishDownloading() {
      downloadFinished();
    }
  }

  private class PosterCallback implements DownloadCallback<DownloadPosterData> {

    @Override public void errorFromDownload(final ResponseError error) {
    }

    @Override public void dataFromDownload(final DownloadPosterData posterData) {
      posterDownloaded(posterData);
    }

    @Override public void finishDownloading() {
      downloadFinished();
    }
  }
}

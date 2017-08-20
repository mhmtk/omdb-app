package com.mhmt.movies.ui.main;

import android.os.AsyncTask;

import com.mhmt.movies.helper.PosterLruCache;
import com.mhmt.movies.domain.DownloadPosterData;
import com.mhmt.movies.domain.Movie;
import com.mhmt.movies.jobs.DownloadCallback;
import com.mhmt.movies.jobs.DownloadMovies;
import com.mhmt.movies.jobs.DownloadPoster;
import com.mhmt.movies.jobs.ResponseError;
import com.mhmt.movies.ui.base.BasePresenter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivityPresenter extends BasePresenter<MainMvpView> implements MainMvpPresenter<MainMvpView>{

  private PosterLruCache memoryCache;
  private PosterCallback posterCallback;
  private MoviesCallback moviesCallback;
  private HashMap<String, AsyncTask> activeDownloads;


  @Override public void onCreate(final MainMvpView mvpView) {
    super.onCreate(mvpView);
    getMvpView().initiateUI();
    memoryCache = PosterLruCache.getInstance();
    this.posterCallback = new PosterCallback();
    this.moviesCallback = new MoviesCallback();
    activeDownloads = new HashMap<>();
  }

  @Override public void onResume() {

  }

  @Override public void onPause() {

  }

  @Override public void onDestroy() {
    super.onDestroy();
    this.posterCallback = null;
    this.moviesCallback = null;
    cancelTasks();
  }

  private void cancelTasks() {
    for (String key : activeDownloads.keySet()) {
      activeDownloads.get(key).cancel(true);
    }
    activeDownloads.clear();
  }

  @Override public void onSearchClicked(final String query) {
    getMvpView().showProgressDialog();
    getMvpView().hideSoftKeyboard();
    new DownloadMovies(moviesCallback).execute(query);
  }

  @Override public void movieSelected(final Movie movie) {
    getMvpView().startMovieDetailActivity(movie);
    getMvpView().hideSoftKeyboard();
  }

  @Override public void onBind(final Movie movie) {
    if (!memoryCache.contains(movie.getImdbID()) && !activeDownloads.containsKey(movie.getImdbID())) {
      final AsyncTask downloadTask = new DownloadPoster(posterCallback, movie.getImdbID()).execute(movie.getPoster());
      activeDownloads.put(movie.getImdbID(), downloadTask);
    }
  }

  @Override public void onSearchCollapsed() {
    getMvpView().updateData(Collections.<Movie>emptyList());
    cancelTasks();
  }

  private void downloadError(final ResponseError error) {
    if (error == ResponseError.MOVIES) {
      getMvpView().dismissProgressDialog();
      getMvpView().updateData(Collections.<Movie>emptyList());
      getMvpView().displayNoResultError();
    }
  }

  private void posterDownloaded(final DownloadPosterData downloadPosterData) {
    activeDownloads.remove(downloadPosterData.getMovieImdbId());
    memoryCache.addBitmapToMemoryCache(downloadPosterData.getMovieImdbId(), downloadPosterData.getPosterBitmap());
    if (getMvpView() != null) {
      getMvpView().reloadMovie(downloadPosterData.getMovieImdbId());
    }
  }

  private void moviesDownloaded(final List<Movie> movies) {
    getMvpView().updateData(movies);
    if (movies.size() == 0) {
      getMvpView().displayNoResultError();
    }
  }

  private void downloadFinished() {
    getMvpView().dismissProgressDialog();
  }

  private class MoviesCallback implements DownloadCallback<List<Movie>> {

    @Override public void errorFromDownload(final ResponseError error) {
      downloadError(error);
    }

    @Override public void dataFromDownload(final List<Movie> movies) {
      moviesDownloaded(movies);
    }

    @Override public void finishDownloading() {
      downloadFinished();
    }
  }

  private class PosterCallback implements DownloadCallback<DownloadPosterData> {

    @Override public void errorFromDownload(final ResponseError error) {
      downloadError(error);
    }

    @Override public void dataFromDownload(final DownloadPosterData posterData) {
      posterDownloaded(posterData);
    }

    @Override public void finishDownloading() {
      downloadFinished();
    }
  }
}

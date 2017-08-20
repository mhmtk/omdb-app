package com.mhmt.movies.ui.main;

import com.mhmt.movies.domain.Movie;
import com.mhmt.movies.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

  void initiateUI();

  void startMovieDetailActivity(final Movie movie);

  void updateData(List<Movie> movies);

  void reloadMovie(String movieImdbId);

  void displayNoResultError();
}

package com.mhmt.movies.ui.moviedetail;

import android.graphics.Bitmap;

import com.mhmt.movies.domain.Movie;
import com.mhmt.movies.ui.base.MvpView;

public interface MovieDetailMvpView extends MvpView {

  void updateData(Movie movie);

  void showPoster(Bitmap bitmapFromMemCache);
}

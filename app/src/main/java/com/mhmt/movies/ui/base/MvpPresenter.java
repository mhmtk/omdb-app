package com.mhmt.movies.ui.base;

public interface MvpPresenter<V extends MvpView> {

  void onCreate(V mvpView);

  void onResume();

  void onPause();

  void onDestroy();

}

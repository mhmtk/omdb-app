package com.mhmt.movies.ui.main;

import com.mhmt.movies.domain.Movie;
import com.mhmt.movies.ui.base.MvpPresenter;

public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

  void onSearchClicked(String searchText);

  void movieSelected(Movie movie);

  void onBind(Movie movie);

  void onSearchCollapsed();
}

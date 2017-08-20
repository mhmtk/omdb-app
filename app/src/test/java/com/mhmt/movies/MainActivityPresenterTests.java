package com.mhmt.movies;

import com.mhmt.movies.domain.Movie;
import com.mhmt.movies.ui.main.MainActivityPresenter;
import com.mhmt.movies.ui.main.MainMvpView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTests {

  @Mock MainMvpView mainMvpView;
  @Mock Movie movie;

  private MainActivityPresenter mainActivityPresenter;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mainActivityPresenter = new MainActivityPresenter();
    mainActivityPresenter.onCreate(mainMvpView);
  }

  @Test
  public void movieSelect_startsDetailActivity(){
    mainActivityPresenter.movieSelected(movie);
    verify(mainMvpView).startMovieDetailActivity(movie);
  }

  @Test
  public void movieSelect_hidesKeyboard(){
    mainActivityPresenter.movieSelected(movie);
    verify(mainMvpView).hideSoftKeyboard();
  }

  @Test
  public void searchCollapse_clearsData() {
    mainActivityPresenter.onSearchCollapsed();
    verify(mainMvpView).updateData(Collections.<Movie>emptyList());
  }
}
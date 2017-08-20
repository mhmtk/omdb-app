package com.mhmt.movies;

import com.mhmt.movies.ui.main.MainActivity;
import com.mhmt.movies.ui.main.MainActivityPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityTests {

  @Mock MainActivityPresenter mainActivityPresenter;

  private MainActivity mainActivity;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mainActivity = Robolectric.buildActivity(MainActivity.class).create().get();
  }

  @Test
  public void notNull() {
    assertNotNull(mainActivity);
  }

}

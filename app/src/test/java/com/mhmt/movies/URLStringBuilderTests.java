package com.mhmt.movies;

import com.mhmt.movies.helper.ApiUrl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class URLStringBuilderTests {

  @Test
  public void noWhitespace() {
    assertTrue(!ApiUrl.getSearchURL(" test  test ").contains(" "));
  }

  @Test
  public void buildsCorrectly() {
    assertEquals(true, "http://www.omdbapi.com/?s=Guardians+of+the+galaxy&apikey=".concat(BuildConfig.API_KEY).equals(
        ApiUrl.getSearchURL("Guardians of the galaxy")));
  }

}

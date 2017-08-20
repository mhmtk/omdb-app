package com.mhmt.movies.domain;

import java.util.ArrayList;

public class SearchResponse {

  private ArrayList<Movie> search;
  private int totalResults;
  private boolean response;

  public SearchResponse() {
  }

  public SearchResponse(final ArrayList<Movie> search, final int totalResults, final boolean response) {
    this.search = search;
    this.totalResults = totalResults;
    this.response = response;
  }

  public ArrayList<Movie> getSearch() {
    return search;
  }

  public void setSearch(final ArrayList<Movie> search) {
    this.search = search;
  }

  public int getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(final int totalResults) {
    this.totalResults = totalResults;
  }

  public boolean isResponse() {
    return response;
  }

  public void setResponse(final boolean response) {
    this.response = response;
  }
}

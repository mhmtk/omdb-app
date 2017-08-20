package com.mhmt.movies.domain;

public class Rating {

  private String source;
  private String value;

  public Rating() {
  }

  public Rating(final String source, final String value) {
    this.source = source;
    this.value = value;
  }

  public String getSource() {
    return source;
  }

  public void setSource(final String source) {
    this.source = source;
  }

  public String getValue() {
    return value;
  }

  public void setValue(final String value) {
    this.value = value;
  }

}

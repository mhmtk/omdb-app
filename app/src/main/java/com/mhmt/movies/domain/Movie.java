package com.mhmt.movies.domain;

public class Movie {

  private String title;
  private String year;
  private String rated;
  private String released;
  private String runtime;
  private String genre;
  private String director;
  private String writer;
  private String actors;
  private String plot;
  private String language;
  private String country;
  private String awards;
  private String poster;
  private Rating[] ratings;
  private String metaScore;
  private String imdbRating;
  private String imdbVotes;
  private String imdbID;
  private String type;
  private String dvd;
  private String boxOffice;
  private String production;
  private String website;

  public Movie() {

  }

  public Movie(final String title, final String year, final String rated, final String released, final String runtime,
               final String genre,
               final String director, final String writer, final String actors, final String plot,
               final String language, final String country,
               final String awards, final String poster, final Rating[] ratings, final String metaScore,
               final String imdbRating, final String imdbVotes,
               final String imdbID, final String type, final String dvd, final String boxOffice, final String production,
               final String website) {
    this.title = title;
    this.year = year;
    this.rated = rated;
    this.released = released;
    this.runtime = runtime;
    this.genre = genre;
    this.director = director;
    this.writer = writer;
    this.actors = actors;
    this.plot = plot;
    this.language = language;
    this.country = country;
    this.awards = awards;
    this.poster = poster;
    this.ratings = ratings;
    this.metaScore = metaScore;
    this.imdbRating = imdbRating;
    this.imdbVotes = imdbVotes;
    this.imdbID = imdbID;
    this.type = type;
    this.dvd = dvd;
    this.boxOffice = boxOffice;
    this.production = production;
    this.website = website;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getYear() {
    return year;
  }

  public void setYear(final String year) {
    this.year = year;
  }

  public String getRated() {
    return rated;
  }

  public void setRated(final String rated) {
    this.rated = rated;
  }

  public String getReleased() {
    return released;
  }

  public void setReleased(final String released) {
    this.released = released;
  }

  public String getRuntime() {
    return runtime;
  }

  public void setRuntime(final String runtime) {
    this.runtime = runtime;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(final String genre) {
    this.genre = genre;
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(final String director) {
    this.director = director;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(final String writer) {
    this.writer = writer;
  }

  public String getActors() {
    return actors;
  }

  public void setActors(final String actors) {
    this.actors = actors;
  }

  public String getPlot() {
    return plot;
  }

  public void setPlot(final String plot) {
    this.plot = plot;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(final String language) {
    this.language = language;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(final String country) {
    this.country = country;
  }

  public String getAwards() {
    return awards;
  }

  public void setAwards(final String awards) {
    this.awards = awards;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(final String poster) {
    this.poster = poster;
  }

  public Rating[] getRatings() {
    return ratings;
  }

  public void setRatings(final Rating[] ratings) {
    this.ratings = ratings;
  }

  public String getMetaScore() {
    return metaScore;
  }

  public void setMetaScore(final String metaScore) {
    this.metaScore = metaScore;
  }

  public String getImdbRating() {
    return imdbRating;
  }

  public void setImdbRating(final String imdbRating) {
    this.imdbRating = imdbRating;
  }

  public String getImdbVotes() {
    return imdbVotes;
  }

  public void setImdbVotes(final String imdbVotes) {
    this.imdbVotes = imdbVotes;
  }

  public String getImdbID() {
    return imdbID;
  }

  public void setImdbID(final String imdbID) {
    this.imdbID = imdbID;
  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public String getDvd() {
    return dvd;
  }

  public void setDvd(final String dvd) {
    this.dvd = dvd;
  }

  public String getBoxOffice() {
    return boxOffice;
  }

  public void setBoxOffice(final String boxOffice) {
    this.boxOffice = boxOffice;
  }

  public String getProduction() {
    return production;
  }

  public void setProduction(final String production) {
    this.production = production;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(final String website) {
    this.website = website;
  }

}

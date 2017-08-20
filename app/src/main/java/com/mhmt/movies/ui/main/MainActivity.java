package com.mhmt.movies.ui.main;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.mhmt.movies.Constant;
import com.mhmt.movies.R;
import com.mhmt.movies.domain.Movie;
import com.mhmt.movies.ui.OnItemBindListener;
import com.mhmt.movies.ui.base.BaseActivity;
import com.mhmt.movies.ui.moviedetail.MovieDetailActivity;
import com.mhmt.movies.ui.view.MoviesRecyclerView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView, SearchView.OnQueryTextListener,
                                                          View.OnClickListener, OnItemBindListener,
                                                          MenuItemCompat.OnActionExpandListener {

  private MainActivityPresenter mainActivityPresenter;

  @BindView(R.id.recycler_view_movies)
  protected MoviesRecyclerView recyclerView;
  private MoviesAdapter moviesAdapter;
  @BindView(R.id.empty_view)
  protected View emptyView;
  private SearchView searchView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ButterKnife.bind(this);

    mainActivityPresenter = new MainActivityPresenter();
    mainActivityPresenter.onCreate(this);
  }

  @Override protected void onResume() {
    super.onResume();
    mainActivityPresenter.onResume();
  }

  @Override protected void onPause() {
    super.onPause();
    mainActivityPresenter.onPause();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mainActivityPresenter.onDestroy();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    final MenuItem searchItem = menu.findItem(R.id.action_search);
    searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
    SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
    searchView.setIconifiedByDefault(false);
    searchView.setIconified(false);
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    searchView.setOnQueryTextListener(this);
    MenuItemCompat.setOnActionExpandListener(searchItem, this);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    return super.onOptionsItemSelected(item);
  }

  @Override public void initiateUI() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setHasFixedSize(false);
    recyclerView.setEmptyView(emptyView);
    moviesAdapter = new MoviesAdapter(Collections.<Movie>emptyList(), this, this);
    recyclerView.setAdapter(moviesAdapter);
  }

  @Override public void startMovieDetailActivity(final Movie movie) {
    Intent intent = new Intent(this, MovieDetailActivity.class);
    intent.putExtra(Constant.Navigation.IMDB_ID, movie.getImdbID());
    startActivity(intent);
  }

  @Override public void updateData(final List<Movie> movies) {
    moviesAdapter.updateData(movies);
  }

  @Override public void reloadMovie(final String movieImdbId) {
    moviesAdapter.notifyItemChanged(recyclerView.getChildAdapterPosition(recyclerView.findViewWithTag(movieImdbId)));
  }

  @Override public void displayNoResultError() {
    Toast.makeText(this, getString(R.string.toast_no_movies_found), Toast.LENGTH_SHORT).show();
  }

  @Override public void bind(final Movie movie) {
    mainActivityPresenter.onBind(movie);
  }

  @Override public boolean onQueryTextSubmit(final String query) {
    mainActivityPresenter.onSearchClicked(query);
    return false;
  }

  @Override public boolean onQueryTextChange(final String newText) {
    return false;
  }

  @Override public void onClick(final View v) {
    mainActivityPresenter.movieSelected(moviesAdapter.getItemAtPosition(recyclerView.getChildAdapterPosition(v)));
  }

  @Override public boolean onMenuItemActionExpand(final MenuItem item) {
    return true;
  }

  @Override public boolean onMenuItemActionCollapse(final MenuItem item) {
    mainActivityPresenter.onSearchCollapsed();
    return true;
  }
}

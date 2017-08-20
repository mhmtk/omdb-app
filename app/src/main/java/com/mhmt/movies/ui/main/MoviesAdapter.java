package com.mhmt.movies.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhmt.movies.R;
import com.mhmt.movies.domain.Movie;
import com.mhmt.movies.ui.OnItemBindListener;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieItemView> {

  private final OnItemBindListener onItemBindListener;
  private List<Movie> movies;
  private View.OnClickListener onClickListener;

  public MoviesAdapter(List<Movie> movies, View.OnClickListener onClickListener, OnItemBindListener onItemBindListener) {
    this.movies = movies;
    this.onClickListener = onClickListener;
    this.onItemBindListener = onItemBindListener;
  }

  @Override public MovieItemView onCreateViewHolder(final ViewGroup parent, final int viewType) {
    return new MovieItemView(LayoutInflater.from(parent.getContext())
                                       .inflate(R.layout.item_view_movie, parent, false), onClickListener);
  }

  @Override public void onBindViewHolder(final MovieItemView holder, final int position) {
    final Movie movie = movies.get(position);
    holder.bind(movie);
    onItemBindListener.bind(movie);
  }

  @Override public int getItemCount() {
    return movies.size();
  }

  public void updateData(final List<Movie> movies) {
    this.movies = movies;
    notifyDataSetChanged();
  }

  public Movie getItemAtPosition(final int childAdapterPosition) {
    return movies.get(childAdapterPosition);
  }

  public void notifyItemChanged(final Movie movie) {
    notifyItemChanged(movies.indexOf(movie));
  }
}

package com.mhmt.movies.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhmt.movies.helper.PosterLruCache;
import com.mhmt.movies.R;
import com.mhmt.movies.domain.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieItemView extends RecyclerView.ViewHolder {

  @BindView(R.id.text_view_title)
  protected TextView titleText;
  @BindView(R.id.text_view_year)
  protected TextView yearText;
  @BindView(R.id.text_view_director)
  protected TextView directorText;
  @BindView(R.id.imageView)
  protected ImageView imageView;


  public MovieItemView(final View itemView, View.OnClickListener onClickListener) {
    super(itemView);
    ButterKnife.bind(this, itemView);
    itemView.setOnClickListener(onClickListener);
  }

  public void bind(final Movie movie) {
    itemView.setTag(movie.getImdbID());
    titleText.setText(movie.getTitle());
    directorText.setText(movie.getDirector());
    yearText.setText(movie.getYear().trim());
    imageView.setImageBitmap(PosterLruCache.getInstance().getBitmapFromMemCache(movie.getImdbID()));
  }

}
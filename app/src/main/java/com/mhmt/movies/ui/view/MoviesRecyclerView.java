package com.mhmt.movies.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class MoviesRecyclerView extends RecyclerView {

  private View emptyView;

  private AdapterDataObserver emptyObserver = new AdapterDataObserver() {

    @Override
    public void onChanged() {
      Adapter<?> adapter = getAdapter();
      if (adapter != null && emptyView != null) {
        if (adapter.getItemCount() == 0) {
          emptyView.setVisibility(View.VISIBLE);
          MoviesRecyclerView.this.setVisibility(View.GONE);
        } else {
          emptyView.setVisibility(View.GONE);
          MoviesRecyclerView.this.setVisibility(View.VISIBLE);
        }
      }

    }
  };

  public MoviesRecyclerView(Context context) {
    super(context);
  }

  public MoviesRecyclerView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MoviesRecyclerView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  public void setAdapter(Adapter adapter) {
    super.setAdapter(adapter);

    if (adapter != null) {
      adapter.registerAdapterDataObserver(emptyObserver);
    }

    emptyObserver.onChanged();
  }

  public void setEmptyView(View emptyView) {
    this.emptyView = emptyView;
  }

}

package com.mhmt.movies.helper;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import java.util.HashSet;
import java.util.Set;

public class PosterLruCache extends LruCache<String, Bitmap> {

  final static int CACHE_SIZE = (int) (Runtime.getRuntime().maxMemory() / 1024) / 8;
  private static PosterLruCache instance;
  private Set<String> keys;

  public static PosterLruCache getInstance() {
    if (instance == null) {
      instance = new PosterLruCache(CACHE_SIZE);
    }
    return instance;
  }

  private PosterLruCache(final int maxSize) {
    super(maxSize);
    keys = new HashSet<>();
  }

  @Override
  protected int sizeOf(String key, Bitmap bitmap) {
    return bitmap.getByteCount() / 1024;
  }

  public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
    if (getBitmapFromMemCache(key) == null) {
      put(key, bitmap);
      keys.add(key);
    }
  }

  @Override
  protected void entryRemoved(final boolean evicted, final String key, final Bitmap oldValue, final Bitmap newValue) {
    super.entryRemoved(evicted, key, oldValue, newValue);
    keys.remove(key);
  }

  public Bitmap getBitmapFromMemCache(String key) {
    return get(key);
  }

  public boolean contains(final String key) {
    return keys.contains(key);
  }
}

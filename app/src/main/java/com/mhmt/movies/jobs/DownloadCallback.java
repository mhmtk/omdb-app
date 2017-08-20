package com.mhmt.movies.jobs;

public interface DownloadCallback<V> {

  void errorFromDownload(final ResponseError error);

  void dataFromDownload(V data);

  void finishDownloading();

}

package com.mhmt.movies.jobs;

import android.os.AsyncTask;

import com.fasterxml.jackson.databind.JsonNode;
import com.mhmt.movies.helper.JsonObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public abstract class BaseNetworkJob<Callback, Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {

  protected final DownloadCallback<Callback> callback;
  protected final JsonObjectMapper mapper;

  public BaseNetworkJob(DownloadCallback<Callback> callback) {
    this.callback = callback;
    mapper = new JsonObjectMapper();
  }

  protected String fetch(URL url) throws IOException {
    InputStream stream = null;
    HttpURLConnection connection = null;
    String response = null;
    try {
      connection = (HttpURLConnection) url.openConnection();
      connection.setReadTimeout(3000);
      connection.setConnectTimeout(3000);
      connection.setRequestMethod("GET");
      connection.setDoInput(true);
      connection.connect();
      int responseCode = connection.getResponseCode();
      if (responseCode != HttpsURLConnection.HTTP_OK) {
        throw new IOException("HTTP error code: " + responseCode);
      }
      stream = connection.getInputStream();
      if (stream != null) {
        final JsonNode json = mapper.readTree(stream);
        response = json.toString();
      }
    } finally {
      if (stream != null) {
        stream.close();
      }
      if (connection != null) {
        connection.disconnect();
      }
    }
    return response;
  }

}

package com.mhmt.movies.helper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class JsonObjectMapper extends ObjectMapper {

  public JsonObjectMapper() {
    setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
    configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//    DateFormat df = new SimpleDateFormat("dd MMM yyyyy");
//    setDateFormat(df);
  }
}

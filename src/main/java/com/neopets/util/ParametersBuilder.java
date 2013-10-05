package com.neopets.util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class ParametersBuilder {

  private List<NameValuePair> parameters;

  public ParametersBuilder() {
    parameters = new ArrayList<NameValuePair>();
  }

  public ParametersBuilder add(Object key, Object value) {
    parameters.add(new BasicNameValuePair(key.toString(), value.toString()));
    return this;
  }

  public List<NameValuePair> getParameters() {
    return parameters;
  }

}

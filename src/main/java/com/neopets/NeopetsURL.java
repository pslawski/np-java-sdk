package com.neopets;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class NeopetsURL {

  public static final NeopetsURL ROOT = new NeopetsURL("");
  public static final NeopetsURL INDEX = new NeopetsURL("index.phtml");
  public static final NeopetsURL LOGIN = new NeopetsURL("login.phtml");
  public static final NeopetsURL BANK = new NeopetsURL("bank.phtml");
  public static final NeopetsURL PROCESS_BANK = new NeopetsURL("process_bank.phtml");
  public static final NeopetsURL STOCK_MARKET = new NeopetsURL("stockmarket.phtml");
  public static final NeopetsURL STOCK_MARKET_BUY = NeopetsURL.STOCK_MARKET.amendQuery(
          new BasicNameValuePair("type", "buy"));
  public static final NeopetsURL STOCK_MARKET_PORTFOLIO = NeopetsURL.STOCK_MARKET.amendQuery(

  private static final String BASE_URL = "http://www.neopets.com";

  private String path;

  private NeopetsURL(String path) {
    this.path = path;
  }

  public NeopetsURL amendQuery(NameValuePair... pairs) {
    if (pairs.length == 0) {
      return this;
    }
    else {
      try {
        StringBuilder builder = new StringBuilder();
        builder.append(path);
        builder.append("?");

        boolean firstPair = true;
        for (NameValuePair pair : pairs) {
          if (firstPair) {
            firstPair = false;
          } else {
            builder.append("&");
          }
          builder.append(URLEncoder.encode(pair.getName(), "UTF-8"));
          builder.append("=");
          builder.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }
        return new NeopetsURL(builder.toString());

      } catch (UnsupportedEncodingException e) {
        throw new NeopetsClientException(e.getMessage(), e);
      }
    }
  }

  public String getPath() {
    if (path.isEmpty()) {
      return INDEX.getPath();
    }
    return path;
  }

  public static NeopetsURL formURL(String path) {
    if (path.isEmpty()) {
      return ROOT;
    } else {
      return new NeopetsURL(path);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof NeopetsURL)) return false;

    NeopetsURL that = (NeopetsURL) o;

    if (path != null ? !path.equals(that.path) : that.path != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return path != null ? path.hashCode() : 0;
  }

  @Override
  public String toString() {
    if (path.isEmpty()) {
      return BASE_URL;
    } else {
      return BASE_URL + "/" + path;
    }
  }
}

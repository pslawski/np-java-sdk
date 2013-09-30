package com.neopets;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;


public class NeopetsResponse {

  private String contents = null;
  private int statusCode = -1;
  private String redirectedLocation = null;

  public NeopetsResponse(HttpResponse response) throws IOException {
    if (response != null) {
      statusCode = response.getStatusLine().getStatusCode();

      Header header = response.getFirstHeader("Location");
      if (header != null) {
        redirectedLocation = header.getValue();
      }

      HttpEntity entity = response.getEntity();
      if (entity != null) {
        contents = EntityUtils.toString(entity);
        EntityUtils.consume(entity);
      }
    }
  }

  public boolean gotRedirected() {
    return statusCode == 302;
  }

  public String getRedirectedLocation() {
    return redirectedLocation;
  }

  public String getContents() {
    return contents;
  }

  public boolean requiresLogin() {
    return gotRedirected() && redirectedLocation.contains("loginpage.phtml");
  }

}

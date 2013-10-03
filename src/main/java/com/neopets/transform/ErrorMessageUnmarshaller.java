package com.neopets.transform;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ErrorMessageUnmarshaller implements Unmarshaller<String> {

  private static final String ERROR_PREFIX = "Error: ";

  @Override
  public String unmarshall(Document document) {
    Elements elements = document.select("div.errorMessage");

    String errorMessage = null;

    if (!elements.isEmpty()) {
      errorMessage = elements.first().text();

      if (errorMessage.startsWith(ERROR_PREFIX)) {
        errorMessage = errorMessage.substring(ERROR_PREFIX.length());
      }
    }
    return  errorMessage;
  }

}

package com.neopets;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NeopetsErrorResults {

  private static final String ERROR_PREFIX = "Error: ";

  protected Document document;
  private boolean errorOccur;
  private String errorMessage;

  public NeopetsErrorResults(String responseContents) {
    document = Jsoup.parse(responseContents);
    parseErrorMessage();
  }

  private void parseErrorMessage() {
    Elements elements = document.select("div.errorMessage");
    errorOccur = !elements.isEmpty();

    if (errorOccur) {
      errorMessage = elements.first().text();

      if (errorMessage.startsWith(ERROR_PREFIX)) {
        errorMessage = errorMessage.substring(ERROR_PREFIX.length());
      }
    }
    else {
      errorMessage = "";
    }
  }

  public boolean didErrorOccur() {
    return errorOccur;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

}

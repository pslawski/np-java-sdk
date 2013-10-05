package com.neopets;

public enum NeopetsURL {

  ROOT(""),
  INDEX("index.phtml"),
  LOGIN("login.phtml"),
  BANK("bank.phtml"),
  PROCESS_BANK("process_bank.phtml");

  private static final String BASE_URL = "http://www.neopets.com";

  private String path;

  NeopetsURL(String path) {
    this.path = path;
  }

  public String getPath() {
    if (path.isEmpty()) {
      return INDEX.getPath();
    }
    return path;
  }

  public static String formURL(String path) {
    if (path.isEmpty()) {
      return BASE_URL;
    } else {
      return BASE_URL + "/" + path;
    }
  }

  public String toString() {
    return formURL(path);
  }

}

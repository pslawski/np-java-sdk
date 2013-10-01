package com.neopets;

public enum NeopetsURL {

  HOME(""),
  BANK("/bank.phtml"),
  PROCESS_BANK("/process_bank.phtml");

  private String path;

  NeopetsURL(String path) {
    this.path = path;
  }

  public String toString() {
    return "http://www.neopets.com" + path;
  }

}

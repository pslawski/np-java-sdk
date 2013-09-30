package com.neopets.util;

public class ParserUtils {

  private ParserUtils() {}

  public static int parseNeopoints(String text) {
    return Integer.parseInt(text.replaceAll("[ NP,]", ""));
  }

}

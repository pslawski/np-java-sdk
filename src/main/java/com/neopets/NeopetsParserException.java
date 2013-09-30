package com.neopets;


public class NeopetsParserException extends NeopetsClientException {

  public NeopetsParserException(String message) {
    super(message);
  }

  public NeopetsParserException(String message, Throwable t) {
    super(message, t);
  }

}

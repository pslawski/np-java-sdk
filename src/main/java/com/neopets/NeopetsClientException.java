package com.neopets;

public class NeopetsClientException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public NeopetsClientException(String message) {
    super(message);
  }

  public NeopetsClientException(String message, Throwable t) {
    super(message, t);
  }

}

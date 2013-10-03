package com.neopets;

public class NeopetsServiceException extends Exception {

  private static final long serialVersionUID = 1L;

  public NeopetsServiceException(String message) {
    super(message);
  }

  public NeopetsServiceException(String message, Throwable t) {
    super(message, t);
  }

}

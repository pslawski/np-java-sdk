package com.neopets.handlers;

public class ExceptionChainHandler {

  private String errorMessage;

  public ExceptionChainHandler(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public <T extends Exception> ExceptionChainHandler handle(ExceptionHandler<T> handler) throws T {
    handler.handle(errorMessage);
    return this;
  }

}

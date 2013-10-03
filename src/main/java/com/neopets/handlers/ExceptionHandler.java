package com.neopets.handlers;

public interface ExceptionHandler<T extends Exception> {

  public void handle(String errorMessage) throws T;

}

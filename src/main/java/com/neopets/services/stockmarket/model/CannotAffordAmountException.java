package com.neopets.services.stockmarket.model;

import com.neopets.NeopetsServiceException;

public class CannotAffordAmountException extends NeopetsServiceException {

  public CannotAffordAmountException(String message, Throwable t) {
    super(message, t);
  }

  public CannotAffordAmountException(String message) {
    super(message);
  }

}

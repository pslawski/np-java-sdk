package com.neopets.services.stockmarket.model;

import com.neopets.NeopetsServiceException;

public class CannotAffordToSellException extends NeopetsServiceException {

  public CannotAffordToSellException(String message, Throwable t) {
    super(message, t);
  }

  public CannotAffordToSellException(String message) {
    super(message);
  }

}

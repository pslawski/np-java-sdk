package com.neopets.services.stockmarket.model;

import com.neopets.NeopetsServiceException;

public class ExceedAmountLimitException extends NeopetsServiceException {

  public ExceedAmountLimitException(String message, Throwable t) {
    super(message, t);
  }

  public ExceedAmountLimitException(String message) {
    super(message);
  }

}

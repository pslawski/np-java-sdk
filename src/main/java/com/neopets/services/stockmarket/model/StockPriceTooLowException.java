package com.neopets.services.stockmarket.model;

import com.neopets.NeopetsServiceException;

public class StockPriceTooLowException extends NeopetsServiceException {

  public StockPriceTooLowException(String message, Throwable t) {
    super(message, t);
  }

  public StockPriceTooLowException(String message) {
    super(message);
  }

}

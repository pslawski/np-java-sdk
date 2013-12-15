package com.neopets.services.stockmarket.model.handlers;

import com.neopets.handlers.ExceptionHandler;
import com.neopets.services.stockmarket.model.CannotAffordToSellException;

public class CannotAffordToSellExceptionHandler implements ExceptionHandler<CannotAffordToSellException> {

  @Override
  public void handle(String errorMessage) throws CannotAffordToSellException {
    if (errorMessage != null && errorMessage.contains("you do not have enough")) {
      throw new CannotAffordToSellException(errorMessage);
    }
  }

}

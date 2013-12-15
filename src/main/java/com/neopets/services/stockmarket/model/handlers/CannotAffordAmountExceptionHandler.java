package com.neopets.services.stockmarket.model.handlers;

import com.neopets.handlers.ExceptionHandler;
import com.neopets.services.stockmarket.model.CannotAffordAmountException;

public class CannotAffordAmountExceptionHandler implements ExceptionHandler<CannotAffordAmountException> {

  @Override
  public void handle(String errorMessage) throws CannotAffordAmountException {
    if (errorMessage != null && errorMessage.contains("You cannot afford that!")) {
      throw new CannotAffordAmountException(errorMessage);
    }
  }

}

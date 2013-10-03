package com.neopets.services.bank.model.handlers;

import com.neopets.services.bank.model.InvalidNeopointsAmountException;
import com.neopets.handlers.ExceptionHandler;

public class InvalidNeopointsAmountExceptionHandler implements ExceptionHandler<InvalidNeopointsAmountException> {

  @Override
  public void handle(String errorMessage) throws InvalidNeopointsAmountException {
    if (errorMessage != null && errorMessage.contains("You do not have enough Neopoints")) {
      throw new InvalidNeopointsAmountException(errorMessage);
    }
  }

}

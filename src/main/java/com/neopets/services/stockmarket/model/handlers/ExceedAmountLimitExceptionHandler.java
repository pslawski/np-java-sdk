package com.neopets.services.stockmarket.model.handlers;

import com.neopets.handlers.ExceptionHandler;
import com.neopets.services.stockmarket.model.ExceedAmountLimitException;

public class ExceedAmountLimitExceptionHandler implements ExceptionHandler<ExceedAmountLimitException> {

  @Override
  public void handle(String errorMessage) throws ExceedAmountLimitException {
    if (errorMessage != null && errorMessage.contains("the current maximum number of shares you can buy daily")) {
      throw new ExceedAmountLimitException(errorMessage);
    }
  }

}

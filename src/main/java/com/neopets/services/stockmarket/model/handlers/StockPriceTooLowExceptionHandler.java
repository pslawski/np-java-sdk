package com.neopets.services.stockmarket.model.handlers;

import com.neopets.handlers.ExceptionHandler;
import com.neopets.services.stockmarket.model.StockPriceTooLowException;

public class StockPriceTooLowExceptionHandler implements ExceptionHandler<StockPriceTooLowException> {

  @Override
  public void handle(String errorMessage) throws StockPriceTooLowException {
    if (errorMessage != null && errorMessage.contains("due to regulations you cannot buy stock that is trading")) {
      throw new StockPriceTooLowException(errorMessage);
    }
  }

}

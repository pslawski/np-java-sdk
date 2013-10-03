package com.neopets.services.bank.model.handlers;

import com.neopets.services.bank.model.AlreadyClaimedInterestException;
import com.neopets.handlers.ExceptionHandler;

public class AlreadyClaimedInterestExceptionHandler implements ExceptionHandler<AlreadyClaimedInterestException> {

  @Override
  public void handle(String errorMessage) throws AlreadyClaimedInterestException {
    if (errorMessage != null && errorMessage.contains("You have already claimed your interest for today")) {
      throw new AlreadyClaimedInterestException(errorMessage);
    }
  }

}

package com.neopets.services.bank.model;

import com.neopets.NeopetsServiceException;

public class WithdrawalLimitException extends NeopetsServiceException {

  public WithdrawalLimitException(String message) {
    super(message);
  }

}

package com.neopets.services.bank.model;

import com.neopets.NeopetsClientException;

public class WithdrawalLimitException extends NeopetsClientException {

  public WithdrawalLimitException(String message) {
    super(message);
  }

}

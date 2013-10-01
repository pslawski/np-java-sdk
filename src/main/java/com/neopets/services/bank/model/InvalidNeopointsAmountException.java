package com.neopets.services.bank.model;

import com.neopets.NeopetsClientException;

public class InvalidNeopointsAmountException extends NeopetsClientException {

  public InvalidNeopointsAmountException(String message) {
    super(message);
  }

}

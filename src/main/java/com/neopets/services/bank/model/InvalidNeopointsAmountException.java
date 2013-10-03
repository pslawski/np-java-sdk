package com.neopets.services.bank.model;

import com.neopets.NeopetsServiceException;

public class InvalidNeopointsAmountException extends NeopetsServiceException {

  public InvalidNeopointsAmountException(String message) {
    super(message);
  }

}

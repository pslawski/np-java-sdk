package com.neopets.services.bank.model;

import com.neopets.NeopetsServiceException;

public class AlreadyClaimedInterestException extends NeopetsServiceException {

  public AlreadyClaimedInterestException(String message) {
    super(message);
  }

}

package com.neopets.services.bank.model;

import com.neopets.NeopetsClientException;

public class AlreadyClaimedInterestException extends NeopetsClientException {

  public AlreadyClaimedInterestException(String message) {
    super(message);
  }

}

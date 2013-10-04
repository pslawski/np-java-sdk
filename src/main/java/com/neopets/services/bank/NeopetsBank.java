package com.neopets.services.bank;

import com.neopets.services.bank.model.AlreadyClaimedInterestException;
import com.neopets.services.bank.model.GetBankRecordResult;
import com.neopets.services.bank.model.InvalidNeopointsAmountException;
import com.neopets.services.bank.model.WithdrawalLimitException;

public interface NeopetsBank {

  public GetBankRecordResult getBankRecord();

  public void depositNeopoints(int neopoints) throws InvalidNeopointsAmountException;

  public void withdrawNeopoints(int neopoints) throws InvalidNeopointsAmountException, WithdrawalLimitException;

  public void collectInterest() throws AlreadyClaimedInterestException;

}


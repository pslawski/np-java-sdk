package com.neopets.services.bank;

import com.neopets.services.bank.model.AlreadyClaimedInterestException;
import com.neopets.services.bank.model.BankRecordResults;
import com.neopets.services.bank.model.InvalidNeopointsAmountException;
import com.neopets.services.bank.model.WithdrawalLimitException;

import java.io.IOException;

public interface NeopetsBank {

  public BankRecordResults getBankRecord() throws IOException;

  public void depositNeopoints(int neopoints) throws IOException, InvalidNeopointsAmountException;

  public void withdrawNeopoints(int neopoints) throws IOException, WithdrawalLimitException, InvalidNeopointsAmountException;

  public void collectInterest() throws IOException, AlreadyClaimedInterestException;

}


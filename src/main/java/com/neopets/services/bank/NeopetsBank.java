package com.neopets.services.bank;

import com.neopets.services.bank.model.BankRecordResults;

import java.io.IOException;

public interface NeopetsBank {

  public BankRecordResults getBankRecord() throws IOException;

  public void depositNeopoints(int neopoints) throws IOException;

  public void withdrawNeopoints(int neopoints) throws IOException;

  public void collectInterest() throws IOException;

}


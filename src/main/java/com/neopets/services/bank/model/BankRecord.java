package com.neopets.services.bank.model;

public class BankRecord {

  private int currentBalance;
  private boolean collectInterest;

  public BankRecord(int currentBalance, boolean collectInterest) {
    this.currentBalance = currentBalance;
    this.collectInterest = collectInterest;
  }

  public int getCurrentBalance() {
    return currentBalance;
  }

  public boolean canCollectInterest() {
    return collectInterest;
  }

}

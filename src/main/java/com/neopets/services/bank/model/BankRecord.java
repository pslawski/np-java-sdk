package com.neopets.services.bank.model;

public class BankRecord {

  private int currentBalance;
  private boolean collectInterest;

  public int getCurrentBalance() {
    return currentBalance;
  }

  public BankRecord withCurrentBalance(int currentBalance) {
    this.currentBalance = currentBalance;
    return this;
  }

  public void setCurrentBalance(int currentBalance) {
    this.currentBalance = currentBalance;
  }

  public boolean canCollectInterest() {
    return collectInterest;
  }

  public BankRecord withCollectInterest(boolean collectInterest) {
    this.collectInterest = collectInterest;
    return this;
  }

  public void setCollectInterest(boolean collectInterest) {
    this.collectInterest = collectInterest;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BankRecord)) return false;

    BankRecord that = (BankRecord) o;

    if (collectInterest != that.collectInterest) return false;
    if (currentBalance != that.currentBalance) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = currentBalance;
    result = 31 * result + (collectInterest ? 1 : 0);
    return result;
  }

  @Override
  public String toString() {
    return "BankRecord{" +
            "currentBalance=" + currentBalance +
            ", collectInterest=" + collectInterest +
            '}';
  }

}

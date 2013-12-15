package com.neopets.services.stockmarket.model;

public class BuySharesRequest {

  private String referenceCheck;
  private String tickerSymbol;
  private int amount;

  public BuySharesRequest() {}

  public BuySharesRequest(String tickerSymbol, int amount) {
    setTickerSymbol(tickerSymbol);
    setAmount(amount);
  }

  public String getTickerSymbol() {
    return tickerSymbol;
  }

  public void setTickerSymbol(String tickerSymbol) {
    if (tickerSymbol == null) {
      throw new IllegalArgumentException("Ticker symbol cannot be null.");
    }
    this.tickerSymbol = tickerSymbol;
  }

  public BuySharesRequest withTickerSymbol(String tickerSymbol) {
    setTickerSymbol(tickerSymbol);
    return this;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    if (amount < 1 || amount > 99999) {
      throw new IllegalArgumentException("Amount must be between 1 and 99,999.");
    }
    this.amount = amount;
  }

  public BuySharesRequest withAmount(int amount) {
    setAmount(amount);
    return this;
  }

  public String getReferenceCheck() {
    return referenceCheck;
  }

  public void setReferenceCheck(String referenceCheck) {
    this.referenceCheck = referenceCheck;
  }

  public BuySharesRequest withReferenceCheck(String referenceCheck) {
    setReferenceCheck(referenceCheck);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BuySharesRequest)) return false;

    BuySharesRequest that = (BuySharesRequest) o;

    if (amount != that.amount) return false;
    if (referenceCheck != null ? !referenceCheck.equals(that.referenceCheck) : that.referenceCheck != null)
      return false;
    if (tickerSymbol != null ? !tickerSymbol.equals(that.tickerSymbol) : that.tickerSymbol != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = referenceCheck != null ? referenceCheck.hashCode() : 0;
    result = 31 * result + (tickerSymbol != null ? tickerSymbol.hashCode() : 0);
    result = 31 * result + amount;
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BuySharesRequest{");
    sb.append("referenceCheck='").append(referenceCheck).append('\'');
    sb.append(", tickerSymbol='").append(tickerSymbol).append('\'');
    sb.append(", amount=").append(amount);
    sb.append('}');
    return sb.toString();
  }

}

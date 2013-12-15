package com.neopets.services.stockmarket.model;

import java.util.List;

public class Portfolio {

  String referenceCheck;
  List<PurchasedStock> stocks;

  public String getReferenceCheck() {
    return referenceCheck;
  }

  public void setReferenceCheck(String referenceCheck) {
    this.referenceCheck = referenceCheck;
  }

  public Portfolio withReferenceCheck(String referenceCheck) {
    setReferenceCheck(referenceCheck);
    return this;
  }

  public List<PurchasedStock> getStocks() {
    return stocks;
  }

  public void setStocks(List<PurchasedStock> stocks) {
    this.stocks = stocks;
  }

  public Portfolio withStocks(List<PurchasedStock> stocks) {
    setStocks(stocks);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Portfolio portfolio = (Portfolio) o;

    if (referenceCheck != null ? !referenceCheck.equals(portfolio.referenceCheck) : portfolio.referenceCheck != null)
      return false;
    if (stocks != null ? !stocks.equals(portfolio.stocks) : portfolio.stocks != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = referenceCheck != null ? referenceCheck.hashCode() : 0;
    result = 31 * result + (stocks != null ? stocks.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Portfolio{");
    sb.append("referenceCheck='").append(referenceCheck).append('\'');
    sb.append(", stocks=").append(stocks);
    sb.append('}');
    return sb.toString();
  }

}

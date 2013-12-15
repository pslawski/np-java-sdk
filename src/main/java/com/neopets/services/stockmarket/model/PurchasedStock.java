package com.neopets.services.stockmarket.model;

import java.util.List;

public class PurchasedStock {

  private String tickerSymbol;
  private String company;
  private int openPrice;
  private int currentPrice;
  private int amount;
  private int paid;
  List<PurchasedShares> sharesList;

  public void setToSell() {
    setSellAmount(amount);
  }

  public int getSellAmount() {
    int sellAmount = 0;
    for (PurchasedShares shares : sharesList) {
      int sharesSellAmount = shares.getSellAmount();
      if (sharesSellAmount > 0) {
        sellAmount += sharesSellAmount;
      }
    }
    return sellAmount;
  }

  public void setSellAmount(int sellAmount) {

    for (PurchasedShares shares : sharesList) {
      if (sellAmount <= 0) {
        shares.setSellAmount(0);
      }
      else if (shares.getAmount() >= sellAmount) {
        shares.setSellAmount(sellAmount);
        sellAmount = 0;
      }
      else {
        shares.setToSell();
        sellAmount -= shares.getAmount();
      }
    }
  }

  public PurchasedStock withSellAmount(int sellAmount) {
    setSellAmount(sellAmount);
    return this;
  }

  public String getTickerSymbol() {
    return tickerSymbol;
  }

  public void setTickerSymbol(String tickerSymbol) {
    this.tickerSymbol = tickerSymbol;
  }

  public PurchasedStock withTickerSymbol(String tickerSymbol) {
    setTickerSymbol(tickerSymbol);
    return this;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public PurchasedStock withCompany(String company) {
    setCompany(company);
    return this;
  }

  public int getOpenPrice() {
    return openPrice;
  }

  public void setOpenPrice(int openPrice) {
    this.openPrice = openPrice;
  }

  public PurchasedStock withOpenPrice(int openPrice) {
    setOpenPrice(openPrice);
    return this;
  }

  public int getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(int currentPrice) {
    this.currentPrice = currentPrice;
  }

  public PurchasedStock withCurrentPrice(int currentPrice) {
    setCurrentPrice(currentPrice);
    return this;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public PurchasedStock withAmount(int amount) {
    setAmount(amount);
    return this;
  }

  public int getPaid() {
    return paid;
  }

  public void setPaid(int paid) {
    this.paid = paid;
  }

  public PurchasedStock withPaid(int paid) {
    setPaid(paid);
    return this;
  }

  public List<PurchasedShares> getSharesList() {
    return sharesList;
  }

  public void setSharesList(List<PurchasedShares> sharesList) {
    this.sharesList = sharesList;
  }

  public PurchasedStock withSharesList(List<PurchasedShares> sharesList) {
    setSharesList(sharesList);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PurchasedStock that = (PurchasedStock) o;

    if (amount != that.amount) return false;
    if (currentPrice != that.currentPrice) return false;
    if (openPrice != that.openPrice) return false;
    if (paid != that.paid) return false;
    if (company != null ? !company.equals(that.company) : that.company != null) return false;
    if (sharesList != null ? !sharesList.equals(that.sharesList) : that.sharesList != null) return false;
    if (tickerSymbol != null ? !tickerSymbol.equals(that.tickerSymbol) : that.tickerSymbol != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = tickerSymbol != null ? tickerSymbol.hashCode() : 0;
    result = 31 * result + (company != null ? company.hashCode() : 0);
    result = 31 * result + openPrice;
    result = 31 * result + currentPrice;
    result = 31 * result + amount;
    result = 31 * result + paid;
    result = 31 * result + (sharesList != null ? sharesList.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("PurchasedStock{");
    sb.append("tickerSymbol='").append(tickerSymbol).append('\'');
    sb.append(", company='").append(company).append('\'');
    sb.append(", openPrice=").append(openPrice);
    sb.append(", currentPrice=").append(currentPrice);
    sb.append(", amount=").append(amount);
    sb.append(", paid=").append(paid);
    sb.append(", sharesList=").append(sharesList);
    sb.append('}');
    return sb.toString();
  }

}

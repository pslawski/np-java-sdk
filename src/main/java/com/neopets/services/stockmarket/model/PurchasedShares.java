package com.neopets.services.stockmarket.model;

public class PurchasedShares {

  private int amount;
  private int paidPrice;
  private String sellId;
  private int sellAmount = 0;

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public PurchasedShares withAmount(int amount) {
    setAmount(amount);
    return this;
  }

  public int getPaidPrice() {
    return paidPrice;
  }

  public void setPaidPrice(int paidPrice) {
    this.paidPrice = paidPrice;
  }

  public PurchasedShares withPaidPrice(int paidPrice) {
    setPaidPrice(paidPrice);
    return this;
  }

  public String getSellId() {
    return sellId;
  }

  public void setSellId(String sellId) {
    this.sellId = sellId;
  }

  public PurchasedShares withSellId(String sellId) {
    setSellId(sellId);
    return this;
  }

  public int getSellAmount() {
    return sellAmount;
  }

  public void setSellAmount(int sellAmount) {
    this.sellAmount = sellAmount;
  }

  public PurchasedShares withSellAmount(int sellAmount) {
    setSellAmount(sellAmount);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PurchasedShares that = (PurchasedShares) o;

    if (amount != that.amount) return false;
    if (paidPrice != that.paidPrice) return false;
    if (sellId != null ? !sellId.equals(that.sellId) : that.sellId != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = amount;
    result = 31 * result + paidPrice;
    result = 31 * result + (sellId != null ? sellId.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("PurchasedShares{");
    sb.append("amount=").append(amount);
    sb.append(", paidPrice=").append(paidPrice);
    sb.append(", sellId='").append(sellId).append('\'');
    sb.append(", sellAmount=").append(sellAmount);
    sb.append('}');
    return sb.toString();
  }

}

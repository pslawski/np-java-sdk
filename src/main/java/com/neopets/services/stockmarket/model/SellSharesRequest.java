package com.neopets.services.stockmarket.model;

public class SellSharesRequest {

  private Portfolio portfolio;

  public SellSharesRequest(Portfolio portfolio) {
    this.portfolio = portfolio;
  }

  public Portfolio getPortfolio() {
    return portfolio;
  }

  public void setPortfolio(Portfolio portfolio) {
    this.portfolio = portfolio;
  }

  public SellSharesRequest withPortfolio(Portfolio portfolio) {
    setPortfolio(portfolio);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SellSharesRequest that = (SellSharesRequest) o;

    if (portfolio != null ? !portfolio.equals(that.portfolio) : that.portfolio != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return portfolio != null ? portfolio.hashCode() : 0;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("SellSharesRequest{");
    sb.append("portfolio=").append(portfolio);
    sb.append('}');
    return sb.toString();
  }

}

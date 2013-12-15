package com.neopets.services.stockmarket.model;

import com.neopets.PageStatus;

public class GetPortfolioResult {

  private PageStatus pageStatus;
  private Portfolio portfolio;

  public PageStatus getPageStatus() {
    return pageStatus;
  }

  public void setPageStatus(PageStatus pageStatus) {
    this.pageStatus = pageStatus;
  }

  public GetPortfolioResult withPageStatus(PageStatus pageStatus) {
    setPageStatus(pageStatus);
    return this;
  }

  public Portfolio getPortfolio() {
    return portfolio;
  }

  public void setPortfolio(Portfolio portfolio) {
    this.portfolio = portfolio;
  }

  public GetPortfolioResult withPortfolio(Portfolio portfolio) {
    setPortfolio(portfolio);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GetPortfolioResult that = (GetPortfolioResult) o;

    if (pageStatus != null ? !pageStatus.equals(that.pageStatus) : that.pageStatus != null) return false;
    if (portfolio != null ? !portfolio.equals(that.portfolio) : that.portfolio != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = pageStatus != null ? pageStatus.hashCode() : 0;
    result = 31 * result + (portfolio != null ? portfolio.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("GetPortfolioResult{");
    sb.append("pageStatus=").append(pageStatus);
    sb.append(", portfolio=").append(portfolio);
    sb.append('}');
    return sb.toString();
  }

}

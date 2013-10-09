package com.neopets.services.stockmarket.model;

import com.neopets.PageStatus;

import java.util.List;

public class ListAllStocksResult {

  private PageStatus pageStatus;
  private List<Stock> stocks;

  public PageStatus getPageStatus() {
    return pageStatus;
  }

  public void setPageStatus(PageStatus pageStatus) {
    this.pageStatus = pageStatus;
  }

  public ListAllStocksResult withPageStatus(PageStatus pageStatus) {
    setPageStatus(pageStatus);
    return this;
  }

  public List<Stock> getStocks() {
    return stocks;
  }

  public void setStocks(List<Stock> stocks) {
    this.stocks = stocks;
  }

  public ListAllStocksResult withStocks(List<Stock> stocks) {
    setStocks(stocks);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ListAllStocksResult)) return false;

    ListAllStocksResult that = (ListAllStocksResult) o;

    if (pageStatus != null ? !pageStatus.equals(that.pageStatus) : that.pageStatus != null) return false;
    if (stocks != null ? !stocks.equals(that.stocks) : that.stocks != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = pageStatus != null ? pageStatus.hashCode() : 0;
    result = 31 * result + (stocks != null ? stocks.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ListAllStocksResult{");
    sb.append("pageStatus=").append(pageStatus);
    sb.append(", stocks=").append(stocks);
    sb.append('}');
    return sb.toString();
  }
}

package com.neopets.services.stockmarket.model;

public class Stock {

  private String tickerSymbol;
  private String company;
  private int volume;
  private int openPrice;
  private int currentPrice;

  public String getTickerSymbol() {
    return tickerSymbol;
  }

  public void setTickerSymbol(String tickerSymbol) {
    this.tickerSymbol = tickerSymbol;
  }

  public Stock withTickerSymbol(String tickerSymbol) {
    setTickerSymbol(tickerSymbol);
    return this;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public Stock withCompany(String company) {
    setCompany(company);
    return this;
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    this.volume = volume;
  }

  public Stock withVolume(int volume) {
    setVolume(volume);
    return  this;
  }

  public int getOpenPrice() {
    return openPrice;
  }

  public void setOpenPrice(int openPrice) {
    this.openPrice = openPrice;
  }

  public Stock withOpenPrice(int openPrice) {
    setOpenPrice(openPrice);
    return this;
  }

  public int getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(int currentPrice) {
    this.currentPrice = currentPrice;
  }

  public Stock withCurrentPrice(int currentPrice) {
    setCurrentPrice(currentPrice);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Stock)) return false;

    Stock stock = (Stock) o;

    if (currentPrice != stock.currentPrice) return false;
    if (openPrice != stock.openPrice) return false;
    if (volume != stock.volume) return false;
    if (company != null ? !company.equals(stock.company) : stock.company != null) return false;
    if (tickerSymbol != null ? !tickerSymbol.equals(stock.tickerSymbol) : stock.tickerSymbol != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = tickerSymbol != null ? tickerSymbol.hashCode() : 0;
    result = 31 * result + (company != null ? company.hashCode() : 0);
    result = 31 * result + volume;
    result = 31 * result + openPrice;
    result = 31 * result + currentPrice;
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Stock{");
    sb.append("tickerSymbol='").append(tickerSymbol).append('\'');
    sb.append(", company='").append(company).append('\'');
    sb.append(", volume=").append(volume);
    sb.append(", openPrice=").append(openPrice);
    sb.append(", currentPrice=").append(currentPrice);
    sb.append('}');
    return sb.toString();
  }
}

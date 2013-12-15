package com.neopets.services.stockmarket;

import com.neopets.services.stockmarket.model.*;

public interface NeopetsStockMarket {

  public ListAllStocksResult listAllStocks();

  public void buyShares(Stock stock, int amount) throws CannotAffordAmountException,
          ExceedAmountLimitException, StockPriceTooLowException;

  public void buyShares(String tickerSymbol, int amount) throws CannotAffordAmountException,
          ExceedAmountLimitException, StockPriceTooLowException;

}

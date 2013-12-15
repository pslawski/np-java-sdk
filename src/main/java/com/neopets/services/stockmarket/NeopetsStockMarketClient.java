package com.neopets.services.stockmarket;

import com.neopets.NeopetsClient;
import com.neopets.NeopetsRequest;
import com.neopets.NeopetsURL;
import com.neopets.auth.NeopetsCredentials;
import com.neopets.services.stockmarket.model.*;
import com.neopets.services.stockmarket.model.handlers.CannotAffordAmountExceptionHandler;
import com.neopets.services.stockmarket.model.handlers.CannotAffordToSellExceptionHandler;
import com.neopets.services.stockmarket.model.handlers.ExceedAmountLimitExceptionHandler;
import com.neopets.services.stockmarket.model.handlers.StockPriceTooLowExceptionHandler;
import com.neopets.services.stockmarket.model.transform.*;
import com.neopets.transform.ErrorUnmarshaller;
import org.apache.http.message.BasicNameValuePair;

public class NeopetsStockMarketClient extends NeopetsClient implements NeopetsStockMarket {

  public NeopetsStockMarketClient(NeopetsCredentials credentials) {
    super(credentials);
  }

  @Override
  public ListAllStocksResult listAllStocks() {
    NeopetsRequest request = new NeopetsRequest(
            NeopetsURL.STOCK_MARKET.amendQuery(
                    new BasicNameValuePair("type", "list"),
                    new BasicNameValuePair("full", "true")))
            .withToNotBeCached();

    return invoke(request, new ListAllStocksResultUnmarshaller());
  }

  @Override
  public void buyShares(Stock stock, int amount) throws StockPriceTooLowException,
          CannotAffordAmountException, ExceedAmountLimitException {
    buyShares(stock.getTickerSymbol(), amount);
  }

  @Override
  public void buyShares(String tickerSymbol, int amount) throws CannotAffordAmountException,
          ExceedAmountLimitException, StockPriceTooLowException {
    buyShares(new BuySharesRequest(tickerSymbol, amount));
  }

  public void buyShares(BuySharesRequest buySharesRequest) throws CannotAffordAmountException,
            ExceedAmountLimitException, StockPriceTooLowException {

    NeopetsRequest request = new NeopetsRequest(NeopetsURL.STOCK_MARKET_BUY)
        .withToNotBeCached();

    IntermediateBuySharesResult result = invoke(request, new IntermediateBuySharesResultUnmarshaller());

    request = new BuySharesRequestMarshaller().marshall(buySharesRequest
            .withReferenceCheck(result.getReferenceCheck()));

    invoke(request, new ErrorUnmarshaller())
            .handle(new CannotAffordAmountExceptionHandler())
            .handle(new ExceedAmountLimitExceptionHandler())
            .handle(new StockPriceTooLowExceptionHandler());
  }

  @Override
  public GetPortfolioResult getPortfolio() {
    NeopetsRequest request = new NeopetsRequest(NeopetsURL.STOCK_MARKET_PORTFOLIO)
        .withToNotBeCached();

    return invoke(request, new GetPortfolioResultUnmarshaller());
  }

  @Override
  public void sellShares(Portfolio portfolio) throws CannotAffordToSellException {
    sellShares(new SellSharesRequest(portfolio));
  }

  public void sellShares(SellSharesRequest sellSharesRequest) throws CannotAffordToSellException {
    NeopetsRequest request = new SellSharesRequestMarshaller().marshall(sellSharesRequest);

    invoke(request, new ErrorUnmarshaller())
        .handle(new CannotAffordToSellExceptionHandler());
  }

}

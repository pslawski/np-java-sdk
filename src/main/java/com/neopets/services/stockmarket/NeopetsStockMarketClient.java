package com.neopets.services.stockmarket;

import com.neopets.NeopetsClient;
import com.neopets.NeopetsRequest;
import com.neopets.NeopetsURL;
import com.neopets.auth.NeopetsCredentials;
import com.neopets.services.stockmarket.model.ListAllStocksResult;
import com.neopets.services.stockmarket.model.transform.ListAllStocksResultUnmarshaller;
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

}

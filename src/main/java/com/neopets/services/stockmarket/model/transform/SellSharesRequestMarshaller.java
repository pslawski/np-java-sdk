package com.neopets.services.stockmarket.model.transform;

import com.neopets.NeopetsRequest;
import com.neopets.NeopetsURL;
import com.neopets.services.stockmarket.model.Portfolio;
import com.neopets.services.stockmarket.model.PurchasedShares;
import com.neopets.services.stockmarket.model.PurchasedStock;
import com.neopets.services.stockmarket.model.SellSharesRequest;
import com.neopets.transform.Marshaller;
import com.neopets.util.ParametersBuilder;

public class SellSharesRequestMarshaller implements Marshaller<SellSharesRequest> {

  @Override
  public NeopetsRequest marshall(SellSharesRequest in) {
    Portfolio portfolio = in.getPortfolio();

    ParametersBuilder builder = new ParametersBuilder()
        .add("_ref_ck", portfolio.getReferenceCheck());

    for (PurchasedStock stock : portfolio.getStocks()) {
      for (PurchasedShares shares : stock.getSharesList()) {
        builder.add(shares.getSellId(), shares.getSellAmount());
      }
    }

    builder.add("type", "sell");

    return new NeopetsRequest(NeopetsURL.PROCESS_STOCK_MARKET, builder)
        .withOrigin(NeopetsURL.ROOT.toString())
        .withReferer(NeopetsURL.STOCK_MARKET_PORTFOLIO.toString());
  }

}

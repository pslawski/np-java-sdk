package com.neopets.services.stockmarket.model.transform;

import com.neopets.NeopetsRequest;
import com.neopets.NeopetsURL;
import com.neopets.services.stockmarket.model.BuySharesRequest;
import com.neopets.transform.Marshaller;
import com.neopets.util.ParametersBuilder;

public class BuySharesRequestMarshaller implements Marshaller<BuySharesRequest> {

  @Override
  public NeopetsRequest marshall(BuySharesRequest in) {

    ParametersBuilder builder = new ParametersBuilder()
        .add("_ref_ck", in.getReferenceCheck())
        .add("type", "buy")
        .add("ticker_symbol", in.getTickerSymbol())
        .add("amount_shares", in.getAmount());

    return new NeopetsRequest(NeopetsURL.PROCESS_STOCK_MARKET, builder)
        .withOrigin(NeopetsURL.ROOT.toString())
        .withReferer(NeopetsURL.STOCK_MARKET_BUY.toString());
  }

}

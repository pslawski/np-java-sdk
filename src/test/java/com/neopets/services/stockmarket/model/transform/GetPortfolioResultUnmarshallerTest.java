package com.neopets.services.stockmarket.model.transform;

import com.neopets.services.stockmarket.model.GetPortfolioResult;
import com.neopets.services.stockmarket.model.Portfolio;
import com.neopets.services.stockmarket.model.PurchasedShares;
import com.neopets.services.stockmarket.model.PurchasedStock;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GetPortfolioResultUnmarshallerTest {

  @Test
  public void testStockMarketFullStockList() throws Exception {
    URL url = this.getClass().getResource("/html/stock_market_portfolio.html");
    String html = FileUtils.readFileToString(new File(url.getFile()));

    GetPortfolioResult result = new GetPortfolioResultUnmarshaller().unmarshall(Jsoup.parse(html));

    List<PurchasedStock> stocks = new ArrayList<PurchasedStock>();

    List<PurchasedShares> chiaSharesList = new ArrayList<PurchasedShares>();
    chiaSharesList.add(new PurchasedShares()
        .withAmount(10)
        .withPaidPrice(15)
        .withSellId("sell[CHIA][73757579]"));
    chiaSharesList.add(new PurchasedShares()
        .withAmount(5)
        .withPaidPrice(38)
        .withSellId("sell[CHIA][74326090]"));
    stocks.add(new PurchasedStock()
        .withTickerSymbol("CHIA")
        .withCompany("Chia Steel Holdings")
        .withOpenPrice(12)
        .withCurrentPrice(12)
        .withAmount(15)
        .withPaid(340)
        .withSharesList(chiaSharesList));

    List<PurchasedShares> faerSharesList = new ArrayList<PurchasedShares>();
    faerSharesList.add(new PurchasedShares()
        .withAmount(10)
        .withPaidPrice(15)
        .withSellId("sell[FAER][74325709]"));
    stocks.add(new PurchasedStock()
        .withTickerSymbol("FAER")
        .withCompany("Faerie Clothing Ltd.")
        .withOpenPrice(13)
        .withCurrentPrice(12)
        .withAmount(10)
        .withPaid(150)
        .withSharesList(faerSharesList));

    List<PurchasedShares> huwSharesList = new ArrayList<PurchasedShares>();
    huwSharesList.add(new PurchasedShares()
        .withAmount(9)
        .withPaidPrice(15)
        .withSellId("sell[HUW][73767627]"));
    stocks.add(new PurchasedStock()
        .withTickerSymbol("HUW")
        .withCompany("Huberts Hot Dogs")
        .withOpenPrice(15)
        .withCurrentPrice(15)
        .withAmount(9)
        .withPaid(135)
        .withSharesList(huwSharesList));

    assertEquals(result.getPortfolio(),
        new Portfolio()
            .withReferenceCheck("14db541b32b095f0b82b44c7a476d79c")
            .withStocks(stocks));
  }
}

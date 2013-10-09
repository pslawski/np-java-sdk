package com.neopets.services.stockmarket.model.transform;

import com.neopets.services.stockmarket.model.ListAllStocksResult;
import com.neopets.services.stockmarket.model.Stock;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.fest.assertions.api.Assertions.assertThat;

public class ListAllStocksResultUnmarshallerTest {

  @Test
  public void testStockMarketFullStockList() throws Exception {
    URL url = this.getClass().getResource("/html/stock_market_full_stock_list.html");
    String html = FileUtils.readFileToString(new File(url.getFile()));

    ListAllStocksResult result = new ListAllStocksResultUnmarshaller().unmarshall(Jsoup.parse(html));

    assertThat(result.getStocks())
            .hasSize(43)
            .contains(
                    new Stock()
                            .withTickerSymbol("AAVL")
                            .withCompany("Alien Aisha Vending Ltd.")
                            .withVolume(10481)
                            .withOpenPrice(33)
                            .withCurrentPrice(32),
                    new Stock()
                            .withTickerSymbol("ACFI")
                            .withCompany("Achyfi Enterprises")
                            .withVolume(0)
                            .withOpenPrice(12)
                            .withCurrentPrice(12));
  }
}

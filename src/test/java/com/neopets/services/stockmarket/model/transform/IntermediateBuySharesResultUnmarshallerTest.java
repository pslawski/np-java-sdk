package com.neopets.services.stockmarket.model.transform;

import com.neopets.services.stockmarket.model.IntermediateBuySharesResult;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class IntermediateBuySharesResultUnmarshallerTest {

  @Test
  public void testStockMarketBuy() throws Exception {
    URL url = this.getClass().getResource("/html/stock_market_buy.html");
    String html = FileUtils.readFileToString(new File(url.getFile()));

    IntermediateBuySharesResult result = new IntermediateBuySharesResultUnmarshaller().unmarshall(Jsoup.parse(html));

    assertEquals("18cd0f66a2de4920356c716d4f2a8ddb", result.getReferenceCheck());
  }
}

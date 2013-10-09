package com.neopets.services.stockmarket.model.transform;

import com.neopets.NeopetsParserException;
import com.neopets.services.stockmarket.model.ListAllStocksResult;
import com.neopets.services.stockmarket.model.Stock;
import com.neopets.transform.AbstractPageUnmarshaller;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class ListAllStocksResultUnmarshaller extends AbstractPageUnmarshaller<ListAllStocksResult> {

  @Override
  public ListAllStocksResult unmarshall(Document document) {
    return new ListAllStocksResult()
            .withPageStatus(unmarshallPageStatus(document))
            .withStocks(parseStocks(document));
  }

  private List<Stock> parseStocks(Document document) {
    Elements tables = document.select(".content table");
    if (tables.isEmpty()) {
      throw new NeopetsParserException("Cannot find the stock table element.");
    }
    Element table = tables.first();

    List<Stock> stocks = new ArrayList<Stock>();
    for (Element row : table.select("tr + tr")) {
      Elements cells = row.select("td + td");

      stocks.add(new Stock()
              .withTickerSymbol(cells.get(0).text())
              .withCompany(cells.get(1).text())
              .withVolume(Integer.parseInt(cells.get(2).text()))
              .withOpenPrice(Integer.parseInt(cells.get(3).text()))
              .withCurrentPrice(Integer.parseInt(cells.get(4).text())));
    }
    return stocks;
  }

}

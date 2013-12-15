package com.neopets.services.stockmarket.model.transform;

import com.neopets.services.stockmarket.model.GetPortfolioResult;
import com.neopets.services.stockmarket.model.Portfolio;
import com.neopets.services.stockmarket.model.PurchasedShares;
import com.neopets.services.stockmarket.model.PurchasedStock;
import com.neopets.transform.AbstractPageUnmarshaller;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class GetPortfolioResultUnmarshaller extends AbstractPageUnmarshaller<GetPortfolioResult> {

  @Override
  public GetPortfolioResult unmarshall(Document document) {
    return new GetPortfolioResult()
        .withPageStatus(unmarshallPageStatus(document))
        .withPortfolio(parsePortfolio(document));
  }

  public Portfolio parsePortfolio(Document document) {
    Elements forms = document.select("form[action~=process_stockmarket]");

    if (forms.isEmpty()) {
      return new Portfolio()
          .withReferenceCheck(null)
          .withStocks(new ArrayList<PurchasedStock>());
    }

    Element form = forms.first();
    String referenceCheck = form.select("input[name=_ref_ck]").first().attr("value");
    Element table = form.select("table:not(table table)").first();

    Elements rows = table.select("tr:not([id], tr tr)");

    List<PurchasedStock> stocks = new ArrayList<PurchasedStock>();

    for (Element row : rows.subList(2, rows.size() - 2)) {
      Elements cells = row.select("td");
      if (cells.isEmpty()) {
        continue;
      }

      String company = cells.get(0).select("img[title]").attr("title");
      String tickerSymbol = cells.get(1).children().first().text();
      int openPrice = Integer.parseInt(cells.get(2).text());
      int currentPrice = Integer.parseInt(cells.get(3).text());
      int amount = Integer.parseInt(cells.get(5).text());
      int paid = Integer.parseInt(cells.get(6).text());

      stocks.add(new PurchasedStock()
          .withTickerSymbol(tickerSymbol)
          .withCompany(company)
          .withOpenPrice(openPrice)
          .withCurrentPrice(currentPrice)
          .withAmount(amount)
          .withPaid(paid));
    }

    rows = table.select("tr[id]");

    for (int i = 0; i < rows.size(); i++) {
      Element row = rows.get(i);
      PurchasedStock stock = stocks.get(i);

      List<PurchasedShares> sharesList = new ArrayList<PurchasedShares>();

      Elements innerRows = row.select("tr + tr");
      for (Element innerRow : innerRows) {
        Elements cells = innerRow.select("td");

        if (cells.isEmpty()) {
          continue;
        }

        int amount = Integer.parseInt(cells.get(0).text());
        int paid = Integer.parseInt(cells.get(1).text());
        String sellId = cells.last().select("input").first().attr("name");

        sharesList.add(new PurchasedShares()
            .withAmount(amount)
            .withPaidPrice(paid)
            .withSellId(sellId));
      }

      stock.setSharesList(sharesList);
    }

    return new Portfolio()
        .withReferenceCheck(referenceCheck)
        .withStocks(stocks);
  }
}

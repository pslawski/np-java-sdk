import com.neopets.auth.ClasspathPropertiesFileCredentialsProvider;
import com.neopets.auth.NeopetsCredentialsProvider;
import com.neopets.services.stockmarket.NeopetsStockMarket;
import com.neopets.services.stockmarket.NeopetsStockMarketClient;
import com.neopets.services.stockmarket.model.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NeopetsStockMarketSample {

  public static final String CREDENTIALS_PATH = "/npCredentials.properties";

  public static void main(String[] args) throws Exception {
    NeopetsCredentialsProvider provider = new ClasspathPropertiesFileCredentialsProvider(CREDENTIALS_PATH);
    NeopetsStockMarket market = new NeopetsStockMarketClient(provider.getCredentials());

    List<Stock> stocks = market.listAllStocks().getStocks();

    Collections.sort(stocks, new Comparator<Stock>() {
      @Override
      public int compare(Stock o1, Stock o2) {
        return o1.getCurrentPrice() - o2.getCurrentPrice();
      }
    });

    for (Stock stock : stocks) {
      if (stock.getCurrentPrice() == 15) {
        System.out.println("===========================================");
        System.out.printf("Buying 10 shares of %s at 15 NP per share.\n", stock.getTickerSymbol());
        System.out.println("===========================================");

        market.buyShares(stock, 10);
        break;
      }
    }

    GetPortfolioResult result = market.getPortfolio();
    Portfolio portfolio = result.getPortfolio();

    System.out.println("Portfolio:");
    System.out.println("===========================================");
    for (PurchasedStock stock : portfolio.getStocks()) {
      System.out.printf("Stock: %s\tAmount: %d\tPaid: %d\n",
          stock.getTickerSymbol(), stock.getAmount(), stock.getPaid());
    }

    boolean sellingShares = false;
    PurchasedStock stockToSell = null;
    int amountToSell = 5;

    for (PurchasedStock stock : portfolio.getStocks()) {
      if (stock.getCurrentPrice() > 15 && stock.getAmount() >= amountToSell) {
        sellingShares = true;
        stockToSell = stock;

        for (PurchasedShares shares : stock.getSharesList()) {
          if (shares.getAmount() >= amountToSell) {
            shares.setSellAmount(amountToSell);
          }
          else {
            shares.setSellAmount(shares.getAmount());
            amountToSell -= shares.getAmount();
          }
        }
        break;
      }
    }

    if (sellingShares) {
      System.out.println("===========================================");
      System.out.printf("Selling %d shares of %s at %d NP per share.\n",
          amountToSell, stockToSell.getTickerSymbol(), stockToSell.getCurrentPrice());
      System.out.println("===========================================");

      market.sellShares(portfolio);
    }
  }

}

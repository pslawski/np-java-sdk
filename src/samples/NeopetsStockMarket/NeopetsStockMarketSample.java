import com.neopets.auth.ClasspathPropertiesFileCredentialsProvider;
import com.neopets.auth.NeopetsCredentialsProvider;
import com.neopets.services.stockmarket.NeopetsStockMarket;
import com.neopets.services.stockmarket.NeopetsStockMarketClient;
import com.neopets.services.stockmarket.model.Stock;

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
      System.out.println(stock);
    }
  }

}

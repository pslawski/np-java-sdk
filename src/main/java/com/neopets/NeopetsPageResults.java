package com.neopets;


import com.neopets.util.ParserUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NeopetsPageResults {
  protected Document document;
  private int neopointsOnHand;

  public NeopetsPageResults(String responseContents) {
    document = Jsoup.parse(responseContents);
    neopointsOnHand = parseNeopointsOnHand();
  }

  private int parseNeopointsOnHand() {
    Elements elements = document.select("a[id=npanchor][href$=inventory.phtml]");
    if (elements.size() != 1) {
      throw new NeopetsParserException("Could not parse Neopoints on hand.");
    }
    return ParserUtils.parseNeopoints(elements.get(0).text());
  }

  public int getNeopointsOnHand() {
    return neopointsOnHand;
  }
}

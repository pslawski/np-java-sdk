package com.neopets.transform;

import com.neopets.NeopetsParserException;
import com.neopets.PageStatus;
import com.neopets.util.ParserUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public abstract class AbstractPageUnmarshaller<T> implements Unmarshaller<T> {

  protected PageStatus unmarshallPageStatus(Document document) {
    PageStatus pageStatus = new PageStatus();
    pageStatus.setNeopointsOnHand(unmarshallNeopointsOnHand(document));
    return pageStatus;
  }

  private int unmarshallNeopointsOnHand(Document document) {
    Elements elements = document.select("a[id=npanchor][href$=inventory.phtml]");
    if (elements.size() != 1) {
      throw new NeopetsParserException("Could not parse Neopoints on hand.");
    }
    return ParserUtils.parseNeopoints(elements.get(0).text());
  }

}

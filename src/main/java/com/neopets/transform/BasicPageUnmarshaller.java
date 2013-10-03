package com.neopets.transform;

import com.neopets.NeopetsPageResults;
import org.jsoup.nodes.Document;

public class BasicPageUnmarshaller extends AbstractPageUnmarshaller<NeopetsPageResults> {

  public NeopetsPageResults unmarshall(Document document) {
    NeopetsPageResults results = new NeopetsPageResults();
    results.setPageStatus(unmarshallPageStatus(document));
    return results;
  }

}

package com.neopets.transform;

import com.neopets.NeopetsPageResult;
import org.jsoup.nodes.Document;

public class BasicPageUnmarshaller extends AbstractPageUnmarshaller<NeopetsPageResult> {

  public NeopetsPageResult unmarshall(Document document) {
    NeopetsPageResult results = new NeopetsPageResult();
    results.setPageStatus(unmarshallPageStatus(document));
    return results;
  }

}

package com.neopets.services.stockmarket.model.transform;

import com.neopets.NeopetsParserException;
import com.neopets.services.stockmarket.model.IntermediateBuySharesResult;
import com.neopets.transform.AbstractPageUnmarshaller;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class IntermediateBuySharesResultUnmarshaller extends AbstractPageUnmarshaller<IntermediateBuySharesResult> {

  @Override
  public IntermediateBuySharesResult unmarshall(Document document) {
    return new IntermediateBuySharesResult()
            .withPageStatus(unmarshallPageStatus(document))
            .withReferenceCheck(parseReferenceCheck(document));
  }

  private String parseReferenceCheck(Document document) {
    Elements inputs = document.select("form[action=process_stockmarket.phtml] > input[name=_ref_ck]");
    if (inputs.isEmpty()) {
      throw new NeopetsParserException("Cannot parse reference check input element.");
    }
    String referenceCheck = inputs.first().attr("value");
    if (referenceCheck == null) {
      throw new NeopetsParserException("Cannot parse the value attribute from reference check input element.");
    }
    return referenceCheck;
  }

}

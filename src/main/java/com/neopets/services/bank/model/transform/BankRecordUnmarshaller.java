package com.neopets.services.bank.model.transform;

import com.neopets.transform.AbstractPageUnmarshaller;
import com.neopets.NeopetsParserException;
import com.neopets.services.bank.model.BankRecord;
import com.neopets.services.bank.model.BankRecordResults;
import com.neopets.util.ParserUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BankRecordUnmarshaller extends AbstractPageUnmarshaller<BankRecordResults> {

  @Override
  public BankRecordResults unmarshall(Document document) {
    BankRecordResults results = new BankRecordResults();

    results.setPageStatus(unmarshallPageStatus(document));
    results.setBankRecord(unmarshallBankRecord(document));

    return results;
  }

  private BankRecord unmarshallBankRecord(Document document) {
    BankRecord bankRecord = new BankRecord();
    bankRecord.setCurrentBalance(unmarshallCurrentBalance(document));
    bankRecord.setCollectInterest(unmarshallInterestCollected(document));
    return bankRecord;
  }

  private int unmarshallCurrentBalance(Document document) throws NeopetsParserException {
    Elements elements = document.select("td:matchesOwn(Current Balance:)");
    if (elements.size() != 1) {
      throw new NeopetsParserException("Could not parse the current balance.");
    }
    Element currentBalanceElement = elements.get(0).nextElementSibling();
    return ParserUtils.parseNeopoints(currentBalanceElement.text());
  }

  private boolean unmarshallInterestCollected(Document document) {
    Elements elements = document.select("input[type=submit][value^=Collect Interest]");
    return elements.size() == 1;
  }

}

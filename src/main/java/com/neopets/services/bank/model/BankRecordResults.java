package com.neopets.services.bank.model;

import com.neopets.NeopetsPageResults;
import com.neopets.NeopetsParserException;
import com.neopets.util.ParserUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BankRecordResults extends NeopetsPageResults {

  private BankRecord bankRecord;

  public BankRecordResults(String responseContents) throws NeopetsParserException {
    super(responseContents);
    int currentBalance = parseCurrentBalance();
    boolean collectInterest = parseInterestCollected();
    bankRecord = new BankRecord(currentBalance, collectInterest);
  }

  private int parseCurrentBalance() throws NeopetsParserException {
    Elements elements = document.select("td:matchesOwn(Current Balance:)");
    if (elements.size() != 1) {
      throw new NeopetsParserException("Could not parse the current balance.");
    }
    Element currentBalanceElement = elements.get(0).nextElementSibling();
    return ParserUtils.parseNeopoints(currentBalanceElement.text());
  }

  private boolean parseInterestCollected() {
    Elements elements = document.select("input[type=submit][value^=Collect Interest]");
    return elements.size() == 1;
  }

  public BankRecord getBankRecord() {
    return bankRecord;
  }

}


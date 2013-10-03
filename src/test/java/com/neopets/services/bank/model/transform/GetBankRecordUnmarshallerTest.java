package com.neopets.services.bank.model.transform;

import com.neopets.PageStatus;
import com.neopets.services.bank.model.BankRecord;
import com.neopets.services.bank.model.GetBankRecordResult;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class GetBankRecordUnmarshallerTest {

  @Test
  public void testBankInterestAlreadyCollected() throws Exception {
    URL url = this.getClass().getResource("/html/bank_interest_already_collected.html");
    String html = FileUtils.readFileToString(new File(url.getFile()));

    GetBankRecordResult results = new GetBankRecordResultUnmarshaller().unmarshall(Jsoup.parse(html));

    GetBankRecordResult expected = new GetBankRecordResult()
            .withPageStatus(new PageStatus()
                    .withNeopointsOnHand(22812))
            .withBankRecord(new BankRecord()
                    .withCurrentBalance(2075334)
                    .withCollectInterest(false));

    assertEquals(expected, results);
  }

  @Test
  public void testBankInterestNotCollectedYet() throws Exception {
    URL url = this.getClass().getResource("/html/bank_interest_not_collected_yet.html");
    String html = FileUtils.readFileToString(new File(url.getFile()));

    GetBankRecordResult results = new GetBankRecordResultUnmarshaller().unmarshall(Jsoup.parse(html));

    GetBankRecordResult expected = new GetBankRecordResult()
            .withPageStatus(new PageStatus()
                    .withNeopointsOnHand(21312))
            .withBankRecord(new BankRecord()
                    .withCurrentBalance(2074708)
                    .withCollectInterest(true));

    assertEquals(expected, results);
  }

}

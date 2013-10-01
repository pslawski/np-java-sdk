package com.neopets.services.bank.model;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BankRecordResultsTest {

  @Test
  public void testBankInterestAlreadyCollected() throws Exception {
    URL url = this.getClass().getResource("/html/bank_interest_already_collected.html");
    String html = FileUtils.readFileToString(new File(url.getFile()));

    BankRecordResults results = new BankRecordResults(html);
    BankRecord record = results.getBankRecord();

    assertEquals(2075334, record.getCurrentBalance());
    assertFalse(record.canCollectInterest());
  }

  @Test
  public void testBankInterestNotCollectedYet() throws Exception {
    URL url = this.getClass().getResource("/html/bank_interest_not_collected_yet.html");
    String html = FileUtils.readFileToString(new File(url.getFile()));

    BankRecordResults results = new BankRecordResults(html);
    BankRecord record = results.getBankRecord();

    assertEquals(2074708, record.getCurrentBalance());
    assertTrue(record.canCollectInterest());
  }

}

package com.neopets;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NeopetsErrorResultsTest {

  @Test
  public void testErrorResult() throws Exception {
    URL url = this.getClass().getResource("/html/deposit_error.html");
    String html = FileUtils.readFileToString(new File(url.getFile()));

    NeopetsErrorResults results = new NeopetsErrorResults(html);

    assertTrue(results.didErrorOccur());
    assertEquals("You do not have enough Neopoints on hand to deposit 1,000,000 NP!", results.getErrorMessage());
  }

  @Test
  public void testNoErrorResult() throws  Exception {
    NeopetsErrorResults results = new NeopetsErrorResults("");

    assertFalse(results.didErrorOccur());
    assertEquals("", results.getErrorMessage());
  }

}

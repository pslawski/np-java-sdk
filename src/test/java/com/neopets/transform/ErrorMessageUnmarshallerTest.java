package com.neopets.transform;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ErrorMessageUnmarshallerTest {

  @Test
  public void testErrorResult() throws Exception {
    URL url = this.getClass().getResource("/html/deposit_error.html");
    String html = FileUtils.readFileToString(new File(url.getFile()));

    String errorMessage = new ErrorMessageUnmarshaller().unmarshall(Jsoup.parse(html));

    assertEquals("You do not have enough Neopoints on hand to deposit 1,000,000 NP!", errorMessage);
  }

  @Test
  public void testNoErrorResult() throws  Exception {
    String errorMessage = new ErrorMessageUnmarshaller().unmarshall(Jsoup.parse(""));
    assertEquals(null, errorMessage);
  }

}

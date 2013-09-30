package com.neopets;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class NeopetsPageResultsTest {

  private NeopetsPageResults results;

  @Before
  public void setUp() throws Exception {
    URL url = this.getClass().getResource("/html/welcome.html");
    System.out.println(url);
    String html = FileUtils.readFileToString(new File(url.getFile()));
    results = new NeopetsPageResults(html);
  }

  @Test
  public void testGetNeopointsOnHand() throws Exception {
    assertEquals(results.getNeopointsOnHand(), 31366);
  }

}

package com.neopets.transform;

import com.neopets.NeopetsPageResults;
import com.neopets.PageStatus;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class BasicPageUnmarshallerTest {

  @Test
  public void testWelcomePage() throws Exception {
    URL url = this.getClass().getResource("/html/welcome.html");
    String html = FileUtils.readFileToString(new File(url.getFile()));

    BasicPageUnmarshaller unmarshaller = new BasicPageUnmarshaller();
    NeopetsPageResults results = unmarshaller.unmarshall(Jsoup.parse(html));

    NeopetsPageResults expected = new NeopetsPageResults()
            .withPageStatus(new PageStatus()
              .withNeopointsOnHand(31366));

    assertEquals(expected, results);
  }

}

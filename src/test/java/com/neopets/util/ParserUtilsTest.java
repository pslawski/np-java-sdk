package com.neopets.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserUtilsTest {

  @Test
  public void testParseNeopoints() throws Exception {
    assertEquals(ParserUtils.parseNeopoints("1,000,000 NP"), 1000000);
    assertEquals(ParserUtils.parseNeopoints("500 NP"), 500);
    assertEquals(ParserUtils.parseNeopoints("8,000"), 8000);
  }

}

package com.neopets.transform;

import com.neopets.handlers.ExceptionChainHandler;
import org.jsoup.nodes.Document;

/**
 * Created with IntelliJ IDEA.
 * User: peter
 * Date: 2013-10-03
 * Time: 2:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ErrorUnmarshaller implements Unmarshaller<ExceptionChainHandler> {

  @Override
  public ExceptionChainHandler unmarshall(Document document) {
    String errorMessage = new ErrorMessageUnmarshaller().unmarshall(document);
    return new ExceptionChainHandler(errorMessage);
  }
}

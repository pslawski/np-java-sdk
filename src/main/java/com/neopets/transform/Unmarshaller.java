package com.neopets.transform;

import org.jsoup.nodes.Document;

public interface Unmarshaller<T> {

  public T unmarshall(Document document);

}

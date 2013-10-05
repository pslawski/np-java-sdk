package com.neopets.transform;

import com.neopets.NeopetsRequest;

public interface Marshaller<T> {

  public NeopetsRequest marshall(T in);

}

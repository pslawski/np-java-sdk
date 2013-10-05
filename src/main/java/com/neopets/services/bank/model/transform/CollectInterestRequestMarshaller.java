package com.neopets.services.bank.model.transform;

import com.neopets.NeopetsRequest;
import com.neopets.NeopetsURL;
import com.neopets.services.bank.model.CollectInterestRequest;
import com.neopets.transform.Marshaller;
import com.neopets.util.ParametersBuilder;

public class CollectInterestRequestMarshaller implements Marshaller<CollectInterestRequest> {

  @Override
  public NeopetsRequest marshall(CollectInterestRequest in) {
    ParametersBuilder builder = new ParametersBuilder()
            .add("type", "interest");

    return new NeopetsRequest(NeopetsURL.PROCESS_BANK, builder)
            .withOrigin(NeopetsURL.ROOT.toString())
            .withToNotBeCached();
  }

}

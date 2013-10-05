package com.neopets.services.bank.model.transform;

import com.neopets.NeopetsRequest;
import com.neopets.NeopetsURL;
import com.neopets.services.bank.model.DepositNeopointsRequest;
import com.neopets.transform.Marshaller;
import com.neopets.util.ParametersBuilder;

public class DepositNeopointsRequestMarshaller implements Marshaller<DepositNeopointsRequest> {

  @Override
  public NeopetsRequest marshall(DepositNeopointsRequest in) {
    int neopoints = in.getNeopoints();

    ParametersBuilder builder = new ParametersBuilder()
            .add("type", "deposit")
            .add("amount", neopoints);

    return new NeopetsRequest(NeopetsURL.PROCESS_BANK, builder)
            .withOrigin(NeopetsURL.ROOT.toString())
            .withToNotBeCached();
  }

}

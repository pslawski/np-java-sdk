package com.neopets.services.bank.model.transform;

import com.neopets.NeopetsRequest;
import com.neopets.NeopetsURL;
import com.neopets.services.bank.model.WithdrawNeopointsRequest;
import com.neopets.transform.Marshaller;
import com.neopets.util.ParametersBuilder;

public class WithdrawNeopointsRequestMarshaller implements Marshaller<WithdrawNeopointsRequest> {

  @Override
  public NeopetsRequest marshall(WithdrawNeopointsRequest in) {
    int neopoints = in.getNeopoints();

    ParametersBuilder builder = new ParametersBuilder()
            .add("type", "withdraw")
            .add("amount", neopoints);

    return new NeopetsRequest(NeopetsURL.PROCESS_BANK, builder)
            .withOrigin(NeopetsURL.ROOT.toString())
            .withToNotBeCached();
  }

}

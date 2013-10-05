package com.neopets.services.bank.model.transform;

import com.neopets.NeopetsRequest;
import com.neopets.NeopetsURL;
import com.neopets.services.bank.model.WithdrawNeopointsRequest;
import com.neopets.transform.Marshaller;
import com.neopets.util.ParametersBuilder;
import org.apache.http.NameValuePair;

import java.util.List;

public class WithdrawNeopointsRequestMarshaller implements Marshaller<WithdrawNeopointsRequest> {

  @Override
  public NeopetsRequest marshall(WithdrawNeopointsRequest in) {
    int neopoints = in.getNeopoints();

    List<NameValuePair> parameters = new ParametersBuilder()
            .add("type", "withdraw")
            .add("amount", neopoints)
            .getParameters();

    return new NeopetsRequest(NeopetsURL.PROCESS_BANK, parameters)
            .withOrigin(NeopetsURL.ROOT.toString())
            .withToNotBeCached();
  }

}

package com.neopets.services.bank.model.transform;

import com.neopets.NeopetsRequest;
import com.neopets.NeopetsURL;
import com.neopets.services.bank.model.CollectInterestRequest;
import com.neopets.transform.Marshaller;
import com.neopets.util.ParametersBuilder;
import org.apache.http.NameValuePair;

import java.util.List;

public class CollectInterestRequestMarshaller implements Marshaller<CollectInterestRequest> {

  @Override
  public NeopetsRequest marshall(CollectInterestRequest in) {
    List<NameValuePair> parameters = new ParametersBuilder()
            .add("type", "interest")
            .getParameters();

    return new NeopetsRequest(NeopetsURL.PROCESS_BANK, parameters)
            .withOrigin(NeopetsURL.ROOT.toString())
            .withToNotBeCached();
  }

}

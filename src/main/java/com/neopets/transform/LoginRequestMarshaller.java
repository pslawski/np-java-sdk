package com.neopets.transform;

import com.neopets.LoginRequest;
import com.neopets.NeopetsRequest;
import com.neopets.NeopetsURL;
import com.neopets.auth.NeopetsCredentials;
import com.neopets.util.ParametersBuilder;

public class LoginRequestMarshaller implements Marshaller<LoginRequest> {

  @Override
  public NeopetsRequest marshall(LoginRequest in) {

    NeopetsCredentials credentials = in.getCredentials();
    NeopetsURL url = in.getUrl();

    ParametersBuilder builder = new ParametersBuilder()
            .add("destination", "/" + url.getPath())
            .add("username", credentials.getUsername())
            .add("password", credentials.getPassword());

    return new NeopetsRequest(NeopetsURL.LOGIN, builder)
            .withReferer(url.toString())
            .withOrigin(NeopetsURL.ROOT.toString())
            .withToNotBeCached();
  }

}

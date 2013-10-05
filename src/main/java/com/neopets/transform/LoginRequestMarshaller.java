package com.neopets.transform;

import com.neopets.LoginRequest;
import com.neopets.NeopetsRequest;
import com.neopets.NeopetsURL;
import com.neopets.auth.NeopetsCredentials;
import com.neopets.util.ParametersBuilder;
import org.apache.http.NameValuePair;

import java.util.List;

public class LoginRequestMarshaller implements Marshaller<LoginRequest> {

  @Override
  public NeopetsRequest marshall(LoginRequest in) {

    NeopetsCredentials credentials = in.getCredentials();
    NeopetsURL url = in.getUrl();

    List<NameValuePair> parameters = new ParametersBuilder()
            .add("destination", "/" + url.getPath())
            .add("username", credentials.getUsername())
            .add("password", credentials.getPassword())
            .getParameters();

    return new NeopetsRequest(NeopetsURL.LOGIN, parameters)
            .withReferer(url.toString())
            .withOrigin(NeopetsURL.ROOT.toString())
            .withToNotBeCached();
  }

}

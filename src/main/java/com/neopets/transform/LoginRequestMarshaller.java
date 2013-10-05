package com.neopets.transform;

import com.neopets.LoginRequest;
import com.neopets.NeopetsClientException;
import com.neopets.NeopetsRequest;
import com.neopets.NeopetsURL;
import com.neopets.auth.NeopetsCredentials;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LoginRequestMarshaller implements Marshaller<LoginRequest> {

  @Override
  public NeopetsRequest marshall(LoginRequest in) {

    NeopetsCredentials credentials = in.getCredentials();
    NeopetsURL url = in.getUrl();

    String destination;
    try {
        destination = URLEncoder.encode(URLEncoder.encode("/" + url.getPath(), "UTF-8"), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new NeopetsClientException("Cannot marshal login request: " + e.getMessage(), e);
    }

    String data = "destination=%252F" + destination + "&username=" + credentials.getUsername() +
            "&password=" + credentials.getPassword();

    NeopetsRequest request = new NeopetsRequest(NeopetsURL.LOGIN, data);
    request.setReferer(url.toString());
    request.setOrigin(NeopetsURL.ROOT.toString());
    request.setToNotBeCached();

    return request;
  }

}

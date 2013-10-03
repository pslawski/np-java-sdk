package com.neopets;

import java.io.IOException;

import com.neopets.transform.Unmarshaller;
import org.apache.http.HttpResponse;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DecompressingHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.neopets.auth.NeopetsCredentials;
import com.neopets.NeopetsLoginException.LoginError;
import org.jsoup.Jsoup;


public abstract class NeopetsClient {

  private NeopetsCredentials credentials;
  private CookieJar cookieJar;
  private DefaultHttpClient client;

  public NeopetsClient(NeopetsCredentials credentials) throws IOException {
    this(credentials, null);
  }

  public NeopetsClient(NeopetsCredentials credentials, CookieJar cookieJar) throws IOException {
    this.credentials = credentials;
    this.cookieJar = cookieJar;

    this.client = new DefaultHttpClient();
    HttpClientParams.setRedirecting(client.getParams(), false);
  }

  protected <T> T invoke(NeopetsRequest request, Unmarshaller<T> unmarshaller) throws IOException {
    NeopetsResponse response = execute(request);
    return unmarshaller.unmarshall(Jsoup.parse(response.getContents()));
  }

  private NeopetsResponse execute(NeopetsRequest request) throws IOException {
    if (cookieJar != null) {
      client.setCookieStore(cookieJar.load());
    }
    NeopetsResponse response = send(request);
    if (response.requiresLogin()) {
      login();
      response = send(request);
    }
    if (cookieJar != null) {
      cookieJar.save(client.getCookieStore());
    }
    return response;
  }

  private NeopetsResponse send(NeopetsRequest request) {
    DecompressingHttpClient decompressingClient = new DecompressingHttpClient(client);
    try {
      HttpResponse httpResponse = decompressingClient.execute(request.getHttpRequest());
      return new NeopetsResponse(httpResponse);
    }
    catch (IOException e) {
      throw new NeopetsClientException(e.getMessage(), e);
    }
  }

  private void login() {
    String data = "destination=%252Findex.phtml&username=" + credentials.getUsername() +
                  "&password=" + credentials.getPassword();

    NeopetsRequest request = new NeopetsRequest("http://www.neopets.com/login.phtml", data);
    request.setReferer("http://www.neopets.com/index.phtml");
    request.setOrigin("http://www.neopets.com");
    request.setToNotBeCached();
    NeopetsResponse response = send(request);

    if (response.gotRedirected()) {
      String location = response.getRedirectedLocation();
      if (location.contains("index.phtml")) {
        return;
      }
      if (location.contains("badpassword")) {
        request = new NeopetsRequest("http://www.neopets.com/" + location);
        request.setReferer("http://www.neopets.com/index.phtml");
        request.setToNotBeCached();
        response = send(request);
      }
    }
    throw new NeopetsLoginException(LoginError.parse(response.getContents()));
  }

  public CookieJar getCookieJar() {
    return cookieJar;
  }

  public void setCookieJar(CookieJar cookieJar) {
    this.cookieJar = cookieJar;
  }

}

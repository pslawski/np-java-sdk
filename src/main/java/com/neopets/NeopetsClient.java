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

  public NeopetsClient(NeopetsCredentials credentials) {
    this(credentials, null);
  }

  public NeopetsClient(NeopetsCredentials credentials, CookieJar cookieJar) {
    this.credentials = credentials;
    this.cookieJar = cookieJar;

    this.client = new DefaultHttpClient();
    HttpClientParams.setRedirecting(client.getParams(), false);
  }

  protected <T> T invoke(NeopetsRequest request, Unmarshaller<T> unmarshaller) {
    NeopetsResponse response = execute(request);
    return unmarshaller.unmarshall(Jsoup.parse(response.getContents()));
  }

  private NeopetsResponse execute(NeopetsRequest request) {
    loadCookies();
    NeopetsResponse response = send(request);
    if (response.requiresLogin()) {
      login();
      response = send(request);
    }
    storeCookies();

    return response;
  }

  private void loadCookies() {
    try {
      if (cookieJar != null) {
        client.setCookieStore(cookieJar.load());
      }
    }
    catch (IOException e) {
      throw new NeopetsClientException("Cannot load cookies from the Cookie Jar: " + e.getMessage(), e);
    }
  }

  private void storeCookies() {
    try {
      if (cookieJar != null) {
        cookieJar.save(client.getCookieStore());
      }
    }
    catch (IOException e) {
      throw new NeopetsClientException("Cannot store cookies from the Cookie Jar: " + e.getMessage(), e);
    }
  }


  private NeopetsResponse send(NeopetsRequest request) {
    DecompressingHttpClient decompressingClient = new DecompressingHttpClient(client);
    try {
      HttpResponse httpResponse = decompressingClient.execute(request.getHttpRequest());
      return new NeopetsResponse(httpResponse);
    }
    catch (IOException e) {
      throw new NeopetsClientException("Cannot send HTTP request: " + e.getMessage(), e);
    }
  }

  private void login() {
    String data = "destination=%252Findex.phtml&username=" + credentials.getUsername() +
                  "&password=" + credentials.getPassword();

    NeopetsRequest request = new NeopetsRequest(NeopetsURL.LOGIN, data);
    request.setReferer(NeopetsURL.INDEX.toString());
    request.setOrigin(NeopetsURL.ROOT.toString());
    request.setToNotBeCached();
    NeopetsResponse response = send(request);

    if (response.gotRedirected()) {
      String location = response.getRedirectedLocation();
      if (location.contains(NeopetsURL.INDEX.getPath())) {
        return;
      }
      if (location.contains("badpassword")) {
        request = new NeopetsRequest(NeopetsURL.formURL(location));
        request.setReferer(NeopetsURL.INDEX.toString());
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

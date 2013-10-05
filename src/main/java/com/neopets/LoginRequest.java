package com.neopets;

import com.neopets.auth.NeopetsCredentials;

public class LoginRequest {

  private NeopetsCredentials credentials;
  private NeopetsURL url;

  public LoginRequest(NeopetsURL url, NeopetsCredentials credentials) {
    setUrl(url);
    setCredentials(credentials);
  }

  public NeopetsCredentials getCredentials() {
    return credentials;
  }

  public LoginRequest withCredentials(NeopetsCredentials credentials) {
    setCredentials(credentials);
    return this;
  }

  public void setCredentials(NeopetsCredentials credentials) {
    this.credentials = credentials;
  }

  public NeopetsURL getUrl() {
    return url;
  }

  public LoginRequest withURL(NeopetsURL url) {
    setUrl(url);
    return this;
  }

  public void setUrl(NeopetsURL url) {
    this.url = url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof LoginRequest)) return false;

    LoginRequest that = (LoginRequest) o;

    if (credentials != null ? !credentials.equals(that.credentials) : that.credentials != null) return false;
    if (url != that.url) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = credentials != null ? credentials.hashCode() : 0;
    result = 31 * result + (url != null ? url.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "LoginRequest{" +
            "credentials=" + credentials +
            ", url=" + url +
            '}';
  }
}

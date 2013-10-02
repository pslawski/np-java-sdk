package com.neopets.auth;

public interface NeopetsCredentialsProvider {

  public NeopetsCredentials getCredentials();

  public void refresh();

}

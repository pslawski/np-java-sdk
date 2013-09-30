package com.neopets.auth;


public class BasicNeopetsCredentials implements NeopetsCredentials {

  private String username;
  private String password;

  public BasicNeopetsCredentials(String username, String password) {
    if (username == null) {
      throw new IllegalArgumentException("Username cannot be null.");
    }
    if (password == null) {
      throw new IllegalArgumentException("Password cannot be null.");
    }

    this.username = username;
    this.password = password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

}

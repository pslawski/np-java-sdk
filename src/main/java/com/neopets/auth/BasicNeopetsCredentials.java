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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BasicNeopetsCredentials)) return false;

    BasicNeopetsCredentials that = (BasicNeopetsCredentials) o;

    if (password != null ? !password.equals(that.password) : that.password != null) return false;
    if (username != null ? !username.equals(that.username) : that.username != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = username != null ? username.hashCode() : 0;
    result = 31 * result + (password != null ? password.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "BasicNeopetsCredentials{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}

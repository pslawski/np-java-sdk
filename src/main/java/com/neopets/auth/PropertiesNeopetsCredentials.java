package com.neopets.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesNeopetsCredentials implements NeopetsCredentials {

  private String username;
  private String password;

  public PropertiesNeopetsCredentials(File file) throws IOException, IllegalArgumentException {
    if (!file.exists()) {
      throw new FileNotFoundException("File doesn't exist, " + file.getAbsolutePath() + ".");
    }

    readCredentials(new FileInputStream(file));

    if (username == null || password == null) {
      throw new IllegalArgumentException("The file, " + file.getAbsolutePath() +
              ", doesn't contain the expected properties 'username' and 'password'.");
    }
  }

  public PropertiesNeopetsCredentials(InputStream stream) throws IOException {
    readCredentials(stream);

    if (username == null || password == null) {
      throw new IllegalArgumentException("The inputted properties data doesn't contain the " +
              "expected properties 'username' and 'password'.");
    }
  }

  private void readCredentials(InputStream stream) throws IOException {
    Properties properties = new Properties();
    try {
      properties.load(stream);
    }
    finally {
      try {
        stream.close();
      }
      catch (IOException ignored) {
      }
    }

    username = properties.getProperty("username");
    password = properties.getProperty("password");
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
    if (!(o instanceof PropertiesNeopetsCredentials)) return false;

    PropertiesNeopetsCredentials that = (PropertiesNeopetsCredentials) o;

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
    return "PropertiesNeopetsCredentials{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}

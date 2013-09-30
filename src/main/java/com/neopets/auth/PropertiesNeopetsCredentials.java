package com.neopets.auth;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesNeopetsCredentials implements NeopetsCredentials {

  private String username;
  private String password;

  public PropertiesNeopetsCredentials(File file) throws IOException, IllegalArgumentException {
    if (!file.exists()) {
      throw new FileNotFoundException("File doesn't exist, " + file.getAbsolutePath() + ".");
    }

    FileInputStream stream = new FileInputStream(file);
    try {
      Properties properties = new Properties();
      properties.load(stream);

      username = properties.getProperty("username");
      password = properties.getProperty("password");

      if (username == null || password == null) {
        throw new IllegalArgumentException("The file, " + file.getAbsolutePath()
                + ", doesn't contain the expected properties 'username' and 'password'.");
      }
    }
    finally {
      try {
        stream.close();
      }
      catch (IOException ignored) {
      }
    }

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

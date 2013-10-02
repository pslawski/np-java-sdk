package com.neopets.auth;

import com.neopets.NeopetsClientException;

import java.io.IOException;
import java.io.InputStream;

public class ClasspathPropertiesFileCredentialsProvider implements NeopetsCredentialsProvider {

  private final String credentialsFilePath;

  public ClasspathPropertiesFileCredentialsProvider(String credentialsFilePath) {
    if (credentialsFilePath == null) {
      throw new IllegalArgumentException("Credentials file path cannot be null.");
    }

    this.credentialsFilePath = ensureAbsolutePath(credentialsFilePath);
  }

  private String ensureAbsolutePath(String path) {
    if (path.startsWith("/")) {
      return path;
    }
    else {
      return "/" + path;
    }
  }

  @Override
  public NeopetsCredentials getCredentials() {
    InputStream stream = this.getClass().getResourceAsStream(credentialsFilePath);
    if (stream == null) {
      throw new NeopetsClientException("Unable to load credentials from " + credentialsFilePath + ".");
    }

    try {
      return new PropertiesNeopetsCredentials(stream);
    } catch (IOException e) {
      throw new NeopetsClientException("Unable to load credentials from " + credentialsFilePath + ".", e);
    }
  }

  @Override
  public void refresh() {}

}

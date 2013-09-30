package com.neopets;

public class NeopetsLoginException extends NeopetsClientException {

  public static enum LoginError {
    NEEDS_BIRTHDAY(1, "Account Requires Birthday to Login"),
    WRONG_PASSWORD(1, "Wrong Password Was Entered"),
    NONEXISTANT_USER(1, "User Does Not Exist"),
    TOO_MANY_ATTEMPTS(2, "Too Many Attempts Were Made"),
    UNKNOWN(0, "Unknown Error");

    private int lCode;
    private String lMessage;

    LoginError(int code, String message) {
      lCode = code;
      lMessage = message;
    }

    public int getCode() {
      return lCode;
    }

    public String getMessage() {
      return lMessage;
    }

    public static LoginError parse(String string) {
      LoginError error = LoginError.UNKNOWN;
      if (string == null) {
        throw new IllegalArgumentException("String cannot be null.");
      }
      else if (string.contains("It looks like you haven't logged in for a while")) {
        error = LoginError.NEEDS_BIRTHDAY;
      }
      else if (string.contains("Invalid Password")) {
        error = LoginError.WRONG_PASSWORD;
      }
      else if (string.contains("we did not find an account with that username")) {
        error = LoginError.NONEXISTANT_USER;
      }
      else if (string.contains("you have tried too many times to guess")) {
        error = LoginError.TOO_MANY_ATTEMPTS;
      }
      return error;
    }
  }
  private LoginError loginError;

  public NeopetsLoginException(LoginError error) {
    super(error.toString());
    this.loginError = error;
  }

  public LoginError getLoginError() {
    return loginError;
  }

}

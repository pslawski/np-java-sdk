package com.neopets.services.bank.model;

public class WithdrawNeopointsRequest {

  private int neopoints;

  public WithdrawNeopointsRequest(int neopoints) {
    setNeopoints(neopoints);
  }

  public int getNeopoints() {
    return neopoints;
  }

  public WithdrawNeopointsRequest withNeopoints(int neopoints) {
    setNeopoints(neopoints);
    return this;
  }

  public void setNeopoints(int neopoints) {
    if (neopoints <= 0) {
      throw new IllegalArgumentException("Neopoints cannot be less than or equal to zero.");
    }
    this.neopoints = neopoints;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof WithdrawNeopointsRequest)) return false;

    WithdrawNeopointsRequest that = (WithdrawNeopointsRequest) o;

    return neopoints == that.neopoints;
  }

  @Override
  public int hashCode() {
    return neopoints;
  }

  @Override
  public String toString() {
    return "DepositNeopointsRequest{" +
            "neopoints=" + neopoints +
            '}';
  }
}

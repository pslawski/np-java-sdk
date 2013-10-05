package com.neopets.services.bank.model;

public class DepositNeopointsRequest {

  private int neopoints;

  public DepositNeopointsRequest(int neopoints) {
    setNeopoints(neopoints);
  }

  public int getNeopoints() {
    return neopoints;
  }

  public DepositNeopointsRequest withNeopoints(int neopoints) {
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
    if (!(o instanceof DepositNeopointsRequest)) return false;

    DepositNeopointsRequest that = (DepositNeopointsRequest) o;

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

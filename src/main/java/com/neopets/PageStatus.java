package com.neopets;

public class PageStatus {

  private int neopointsOnHand;

  public int getNeopointsOnHand() {
    return neopointsOnHand;
  }

  public PageStatus withNeopointsOnHand(int neopointsOnHand) {
    this.neopointsOnHand = neopointsOnHand;
    return this;
  }

  public void setNeopointsOnHand(int neopointsOnHand) {
    this.neopointsOnHand = neopointsOnHand;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof PageStatus)) return false;

    PageStatus that = (PageStatus) o;

    if (neopointsOnHand != that.neopointsOnHand) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return neopointsOnHand;
  }

  @Override
  public String toString() {
    return "PageStatus{" +
            "neopointsOnHand=" + neopointsOnHand +
            '}';
  }
}

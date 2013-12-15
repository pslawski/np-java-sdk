package com.neopets.services.stockmarket.model;

import com.neopets.PageStatus;

public class IntermediateBuySharesResult {

  private PageStatus pageStatus;
  private String referenceCheck;

  public PageStatus getPageStatus() {
    return pageStatus;
  }

  public void setPageStatus(PageStatus pageStatus) {
    this.pageStatus = pageStatus;
  }

  public IntermediateBuySharesResult withPageStatus(PageStatus pageStatus) {
    setPageStatus(pageStatus);
    return this;
  }

  public String getReferenceCheck() {
    return referenceCheck;
  }

  public void setReferenceCheck(String referenceCheck) {
    this.referenceCheck = referenceCheck;
  }

  public IntermediateBuySharesResult withReferenceCheck(String referenceCheck) {
    setReferenceCheck(referenceCheck);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof IntermediateBuySharesResult)) return false;

    IntermediateBuySharesResult that = (IntermediateBuySharesResult) o;

    if (pageStatus != null ? !pageStatus.equals(that.pageStatus) : that.pageStatus != null) return false;
    if (referenceCheck != null ? !referenceCheck.equals(that.referenceCheck) : that.referenceCheck != null)
      return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = pageStatus != null ? pageStatus.hashCode() : 0;
    result = 31 * result + (referenceCheck != null ? referenceCheck.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("IntermediateBuySharesResult{");
    sb.append("pageStatus=").append(pageStatus);
    sb.append(", referenceCheck='").append(referenceCheck).append('\'');
    sb.append('}');
    return sb.toString();
  }

}

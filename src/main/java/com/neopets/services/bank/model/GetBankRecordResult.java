package com.neopets.services.bank.model;

import com.neopets.PageStatus;

public class GetBankRecordResult {

  private PageStatus pageStatus;
  private BankRecord bankRecord;

  public PageStatus getPageStatus() {
    return pageStatus;
  }

  public GetBankRecordResult withPageStatus(PageStatus pageStatus) {
    this.pageStatus = pageStatus;
    return this;
  }

  public void setPageStatus(PageStatus pageStatus) {
    this.pageStatus = pageStatus;
  }

  public BankRecord getBankRecord() {
    return bankRecord;
  }

  public GetBankRecordResult withBankRecord(BankRecord bankRecord) {
    this.bankRecord = bankRecord;
    return this;
  }

  public void setBankRecord(BankRecord bankRecord) {
    this.bankRecord = bankRecord;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof GetBankRecordResult)) return false;

    GetBankRecordResult that = (GetBankRecordResult) o;

    if (bankRecord != null ? !bankRecord.equals(that.bankRecord) : that.bankRecord != null) return false;
    if (pageStatus != null ? !pageStatus.equals(that.pageStatus) : that.pageStatus != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = pageStatus != null ? pageStatus.hashCode() : 0;
    result = 31 * result + (bankRecord != null ? bankRecord.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "GetBankRecordResult{" +
            "pageStatus=" + pageStatus +
            ", bankRecord=" + bankRecord +
            '}';
  }

}


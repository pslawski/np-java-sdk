package com.neopets;

public class NeopetsPageResult {

  private PageStatus pageStatus;

  public PageStatus getPageStatus() {
    return pageStatus;
  }

  public NeopetsPageResult withPageStatus(PageStatus pageStatus) {
    this.pageStatus = pageStatus;
    return this;
  }

  public void setPageStatus(PageStatus pageStatus) {
    this.pageStatus = pageStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof NeopetsPageResult)) return false;

    NeopetsPageResult results = (NeopetsPageResult) o;

    if (pageStatus != null ? !pageStatus.equals(results.pageStatus) : results.pageStatus != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return pageStatus != null ? pageStatus.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "NeopetsPageResult{" +
            "pageStatus=" + pageStatus +
            '}';
  }

}

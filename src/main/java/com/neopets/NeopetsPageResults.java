package com.neopets;

public class NeopetsPageResults {

  private PageStatus pageStatus;

  public PageStatus getPageStatus() {
    return pageStatus;
  }

  public NeopetsPageResults withPageStatus(PageStatus pageStatus) {
    this.pageStatus = pageStatus;
    return this;
  }

  public void setPageStatus(PageStatus pageStatus) {
    this.pageStatus = pageStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof NeopetsPageResults)) return false;

    NeopetsPageResults results = (NeopetsPageResults) o;

    if (pageStatus != null ? !pageStatus.equals(results.pageStatus) : results.pageStatus != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    return pageStatus != null ? pageStatus.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "NeopetsPageResults{" +
            "pageStatus=" + pageStatus +
            '}';
  }

}

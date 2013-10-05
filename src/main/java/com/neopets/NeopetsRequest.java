package com.neopets;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.List;


public class NeopetsRequest {

  private HttpRequestBase httpRequest;

  public NeopetsRequest(NeopetsURL url) {
    this(url.toString());
  }

  public NeopetsRequest(String url) {
    httpRequest = new HttpGet(url);
    setDefaultHeaders();
  }

  public NeopetsRequest(NeopetsURL url, String contents, ContentType contentType) {
    this(url.toString(), contents, contentType);
  }


  public NeopetsRequest(String url, String contents, ContentType contentType) {
    this(url, new StringEntity(contents, contentType));
  }

  public NeopetsRequest(NeopetsURL url, List<NameValuePair> parameters) {
    this(url.toString(), parameters);
  }

  public NeopetsRequest(String url, List<NameValuePair> parameters) {
    this(url, newUrlEncodedFormEntity(parameters));
  }

  public NeopetsRequest(String url, HttpEntity entity) {
    HttpPost post = new HttpPost(url);
    post.setEntity(entity);
    httpRequest = post;
    setDefaultHeaders();
  }

  private static HttpEntity newUrlEncodedFormEntity(List<NameValuePair> parameters) {
    try {
      return new UrlEncodedFormEntity(parameters, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new NeopetsClientException("Cannot create request: " + e.getMessage(), e);
    }
  }

  private void setDefaultHeaders() {
    httpRequest.setHeader("Connection", "keep-alive");
    httpRequest.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) " +
      "AppleWebKit/537.22 (KHTML, like Gecko) " +
      "Chrome/25.0.1364.172 Safari/537.22");
    httpRequest.setHeader("Accept-Encoding", "gzip,deflate,sdch");
    httpRequest.setHeader("Accept-Language", "en-US,en;q=0.8");
    httpRequest.setHeader("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");
    setAccept("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
  }

  public void setOrigin(String origin) {
    httpRequest.setHeader("Origin", origin);
  }

  public void setReferer(String referer) {
    httpRequest.setHeader("Referer", referer);
  }

  public void setContentType(String contentType) {
    httpRequest.setHeader("Content-Type", contentType);
  }

  public void setCacheControl(String cacheControl) {
    httpRequest.setHeader("Cache-Control", cacheControl);
  }

  public void setToNotBeCached() {
    setCacheControl("max-age=0");
  }

  public void setAccept(String accept) {
    httpRequest.setHeader("Accept", accept);
  }

  public HttpRequestBase getHttpRequest() {
    return httpRequest;
  }

  public void releaseConnection() {
    httpRequest.releaseConnection();
  }

}

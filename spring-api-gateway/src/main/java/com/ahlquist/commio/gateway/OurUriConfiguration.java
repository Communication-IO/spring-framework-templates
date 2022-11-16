package com.ahlquist.commio.gateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import reactor.netty.http.client.HttpClient.UriConfiguration;

@ConfigurationProperties
public class OurUriConfiguration {

  private String httpbin = "http://communication.io:80";

  public String getHttpbin() {
    return httpbin;
  }

  public void setHttpbin(String httpbin) {
    this.httpbin = httpbin;
  }
}

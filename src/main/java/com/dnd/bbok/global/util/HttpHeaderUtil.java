package com.dnd.bbok.global.util;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import org.springframework.http.HttpHeaders;

/**
 * access Token 을 Header 의 Authorization 에 담는다.
 */
public class HttpHeaderUtil {

  private HttpHeaderUtil() {
  }

  public static void setAccessToken(HttpHeaders headers, String accessToken) {
    // set access token header
    headers.set(AUTHORIZATION, "Bearer " + accessToken);
  }
}

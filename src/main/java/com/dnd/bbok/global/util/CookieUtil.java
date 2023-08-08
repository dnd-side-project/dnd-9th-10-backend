package com.dnd.bbok.global.util;

import static com.dnd.bbok.global.jwt.JwtTokenProvider.REFRESH_TOKEN_EXPIRE_LENGTH_MS;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;


/**
 * Cookie 관련 유틸리티 클래스.
 */
public class CookieUtil {

  private CookieUtil() {
    throw new IllegalStateException("Utility class");
  }

  public static void setRefreshCookie(HttpHeaders httpHeaders, String refreshToken) {

    ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
        .httpOnly(true)
        .secure(true)
        .maxAge(REFRESH_TOKEN_EXPIRE_LENGTH_MS)
        .path("/")
        .sameSite("None")
        .build();

    httpHeaders.add(HttpHeaders.SET_COOKIE, cookie.toString());
  }

  public static void resetRefreshToken(HttpHeaders headers) {
    ResponseCookie cookie = ResponseCookie.from("refreshToken", "")
        .httpOnly(true)
        .secure(true)
        .maxAge(0)
        .path("/")
        .sameSite("None")
        .build();

    headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
  }
}

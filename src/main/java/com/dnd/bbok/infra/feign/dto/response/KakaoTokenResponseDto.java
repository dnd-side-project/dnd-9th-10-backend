package com.dnd.bbok.infra.feign.dto.response;

import lombok.Getter;

/**
 * 카카오 액세스 토큰 정보 응답 Dto
 */
@Getter
public class KakaoTokenResponseDto {

  /**
   * 반환되는 토큰 타입(Bearer 고정)
   */
  private String token_type;

  /**
   * 사용자의 accessToken 값
   */
  private String access_token;

  /**
   * accessToken 만료 시간
   */
  private String expires_in;

  /**
   * 사용자의 refreshToken 값
   */
  private String refresh_token;

  /**
   * refreshToken 만료 시간
   */
  private String refresh_token_expires_in;

  public String getAccessToken() {
    return "Bearer " + access_token;
  }

  @Override
  public String toString() {
    return "액세스 토큰입니다: " + access_token;
  }

}

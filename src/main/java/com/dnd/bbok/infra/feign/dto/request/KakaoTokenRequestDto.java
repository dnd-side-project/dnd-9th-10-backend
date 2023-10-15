package com.dnd.bbok.infra.feign.dto.request;

import com.dnd.bbok.infra.feign.dto.KakaoInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 카카오 액세스 토큰을 얻기 위한 request Dto
 * 액세스 토큰을 얻어야만 사용자 정보를 가져올 수 있다.
 */
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class KakaoTokenRequestDto {

  private String code;
  private String client_id;
  private String client_secret;
  private String redirect_uri;
  private final String grant_type = "authorization_code";

  public static KakaoTokenRequestDto newInstance(KakaoInfo kakaoInfo, String code, String redirectUri) {
    return KakaoTokenRequestDto.builder()
        .client_id(kakaoInfo.getClientId())
        .client_secret(kakaoInfo.getSecretKey())
        .redirect_uri(redirectUri)
        .code(code)
        .build();
  }

  @Override
  public String toString() {
    return
        "code=" + code + '&' +
            "client_id=" + client_id + '&' +
            "client_secret=" + client_secret + '&' +
            "redirect_uri=" + redirect_uri + '&' +
            "grant_type=" + grant_type;
  }

}

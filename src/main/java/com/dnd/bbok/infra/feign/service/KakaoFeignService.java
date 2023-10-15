package com.dnd.bbok.infra.feign.service;

import com.dnd.bbok.infra.feign.client.KakaoInfoClient;
import com.dnd.bbok.infra.feign.client.KakaoTokenClient;
import com.dnd.bbok.infra.feign.dto.KakaoInfo;
import com.dnd.bbok.infra.feign.dto.request.KakaoTokenRequestDto;
import com.dnd.bbok.infra.feign.dto.response.KakaoTokenResponseDto;
import com.dnd.bbok.infra.feign.dto.response.KakaoUserInfoResponse;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoFeignService {

  private final KakaoTokenClient kakaoTokenClient;
  private final KakaoInfoClient kakaoInfoClient;
  private final KakaoInfo kakaoInfo;

  public HttpHeaders kakaoLogin(String redirectUri){
    return createHttpHeader(kakaoInfo.kakaoUrlInit(redirectUri));
  }

  private static HttpHeaders createHttpHeader(String requestUrl) {
    try {
      URI uri = new URI(requestUrl);
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setLocation(uri);
      return httpHeaders;
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 인가 코드를 가지고 토큰을 가져옵니다.
   * @param code 카카오 서버에서 내려준 인가 코드
   * @return 해당 사용자 정보 response dto
   */
  public KakaoUserInfoResponse getKakaoInfoWithToken(String code, String redirectUri) {
    //1. 인가 코드를 가지고 토큰을 가져온다.
    String kakaoToken = getKakaoToken(code, redirectUri);
    //2. 해당 토큰으로 user 정보를 들고온다.
    return getKakaoInfo(kakaoToken);
  }

  /**
   * 카카오 액세스 토큰으로 유저 정보를 요청합니다.
   */
  private KakaoUserInfoResponse getKakaoInfo(String kakaoToken) {
    return kakaoInfoClient.getUserInfo(kakaoToken);
  }

  /**
   * 인가 코드를 가지고 토큰을 가져옵니다.
   * @param code 서버에서 내려주는 인가코드.
   * @return accessToken
   */
  private String getKakaoToken(String code, String redirectUri) {
    KakaoTokenResponseDto token = kakaoTokenClient.getToken(
        KakaoTokenRequestDto.newInstance(kakaoInfo, code, redirectUri).toString());
    return token.getAccessToken();
  }


}

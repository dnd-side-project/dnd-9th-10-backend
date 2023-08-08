package com.dnd.bbok.infra.feign.client;

import com.dnd.bbok.infra.feign.config.KakaoFeignConfig;
import com.dnd.bbok.infra.feign.dto.response.KakaoTokenResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 카카오 토큰 관련 feign client
 */
@FeignClient(name = "kakaoTokenClient", url = "https://kauth.kakao.com", configuration = KakaoFeignConfig.class)
@Component
public interface KakaoTokenClient {

  //https://kauth.kakao.com/oauth/token => 카카오 토큰 받기 url
  @PostMapping(value = "/oauth/token")
  KakaoTokenResponseDto getToken(@RequestBody String kakaoTokenRequestDto);

}

package com.dnd.bbok.infra.feign.client;

import com.dnd.bbok.infra.feign.config.KakaoFeignConfig;
import com.dnd.bbok.infra.feign.dto.response.KakaoUserInfoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 카카오 사용자 정보 얻어오는 feign client
 */
@FeignClient(name = "kakaoInfoClient", url = "https://kapi.kakao.com", configuration = KakaoFeignConfig.class)
@Component
public interface KakaoInfoClient {

  //https://kapi.kakao.com/v2/user/me => 회원 정보 요청 url
  @GetMapping(value = "/v2/user/me")
  KakaoUserInfoResponseDto getUserInfo(@RequestHeader(name = "Authorization") String Authorization);

}

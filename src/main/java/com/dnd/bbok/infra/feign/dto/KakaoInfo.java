package com.dnd.bbok.infra.feign.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * yml 정보를 잇는 클래스
 */
@Slf4j
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "oauth2.kakao")
public class KakaoInfo {

  private String baseUrl;
  private String clientId;
  private String secretKey;

  public String kakaoUrlInit(String redirectUri) {
    Map<String, Object> params = new HashMap<>();
    params.put("redirect_uri", redirectUri);
    params.put("client_id", getClientId());
    params.put("response_type", "code");

    String paramStr = params.entrySet().stream()
        .map(param -> param.getKey() + "=" + param.getValue())
        .collect(Collectors.joining("&"));

    return getBaseUrl()
        +"/oauth/authorize"
        + "?"
        + paramStr;
  }

}

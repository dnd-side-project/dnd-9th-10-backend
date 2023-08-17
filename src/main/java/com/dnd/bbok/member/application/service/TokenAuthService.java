package com.dnd.bbok.member.application.service;

import com.dnd.bbok.member.application.port.in.usecase.GetAuthQuery;
import com.dnd.bbok.member.application.port.out.JwtTokenPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * 요청이 들어왔을 때, filter 를 거치는 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TokenAuthService implements GetAuthQuery {

  private final JwtTokenPort jwtTokenPort;

  @Override
  public Authentication getAuthentication(String accessToken) {
    return jwtTokenPort.getAuthentication(accessToken);
  }
}

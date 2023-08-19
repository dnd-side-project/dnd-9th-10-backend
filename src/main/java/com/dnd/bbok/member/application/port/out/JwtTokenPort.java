package com.dnd.bbok.member.application.port.out;

import com.dnd.bbok.member.domain.Member;
import org.springframework.security.core.Authentication;

public interface JwtTokenPort {

  String createAccessToken(Member member);

  String createRefreshToken(Member member);

  Authentication getAuthentication(String accessToken);

  boolean isTokenExpirationSafe(String refreshToken);

  void saveRefreshTokenInRedis(Member member, String refreshToken);

  void validateToken(String token);

}

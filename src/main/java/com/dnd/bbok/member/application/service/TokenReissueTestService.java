package com.dnd.bbok.member.application.service;

import com.dnd.bbok.infra.redis.RedisService;
import com.dnd.bbok.member.application.port.in.response.ReIssueToken;
import com.dnd.bbok.member.application.port.in.usecase.TokenReissueUseCase;
import com.dnd.bbok.member.application.port.out.JwtTokenPort;
import com.dnd.bbok.member.application.port.out.LoadMemberPort;
import com.dnd.bbok.member.domain.Member;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 토큰 재발급 서비스
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class TokenReissueTestService implements TokenReissueUseCase {

  private final RedisService redisService;
  private final JwtTokenPort jwtTokenPort;
  private final LoadMemberPort loadMemberPort;

  @Override
  public ReIssueToken reIssueToken(String refreshToken) {
    UUID memberId = redisService.findMemberByToken(refreshToken);
    Member member = loadMemberPort.loadById(memberId);
    String newAccessToken = jwtTokenPort.createAccessToken(member);

    if(jwtTokenPort.isTokenExpirationSafe(refreshToken)) {
      return new ReIssueToken(newAccessToken, refreshToken);
    }
    String newRefreshToken = jwtTokenPort.createRefreshToken(member);
    redisService.saveRefreshToken(memberId, newRefreshToken);
    return new ReIssueToken(newAccessToken, newRefreshToken);
  }
}

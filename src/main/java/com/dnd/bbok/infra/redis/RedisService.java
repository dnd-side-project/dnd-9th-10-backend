package com.dnd.bbok.infra.redis;

import static com.dnd.bbok.global.exception.ErrorCode.REFRESH_JWT_EXPIRED;

import com.dnd.bbok.member.application.exception.JwtException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * refreshToken 관리를 위한 Redis 관련 서비스.
 */
@Service
@RequiredArgsConstructor
public class RedisService {

  private final RefreshTokenRepository refreshTokenRepository;

  @Transactional
  public void saveRefreshToken(UUID memberId, String refreshToken) {
    refreshTokenRepository.save(new RefreshToken(memberId, refreshToken));
  }

  @Transactional(readOnly = true)
  public UUID findMemberByToken(String refreshToken) {
    RefreshToken token = refreshTokenRepository.findByRefreshToken(refreshToken)
        .orElseThrow(() -> new JwtException(REFRESH_JWT_EXPIRED));
    return token.getMemberId();
  }
}

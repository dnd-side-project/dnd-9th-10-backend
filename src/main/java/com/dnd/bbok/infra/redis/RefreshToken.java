package com.dnd.bbok.infra.redis;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "memberId", timeToLive = 1000L * 60 * 60 * 24 * 14)
public class RefreshToken {

  @Id
  private UUID memberId;
  private String refreshToken;

  public RefreshToken(UUID memberId, String refreshToken) {
    this.memberId = memberId;
    this.refreshToken = refreshToken;
  }

}

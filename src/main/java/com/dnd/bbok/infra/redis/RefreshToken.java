package com.dnd.bbok.infra.redis;

import java.util.UUID;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "refreshToken", timeToLive = 1000L * 60 * 60 * 24 * 14)
@Getter
public class RefreshToken {

  /**
   * 사용자의 Id
   */
  @Id
  private final UUID memberId;

  /**
   * 사용자가 가지고 있는 refreshToken
   */
  @Indexed
  private final String refreshToken;

  public RefreshToken(UUID memberId, String refreshToken) {
    this.memberId = memberId;
    this.refreshToken = refreshToken;
  }

}

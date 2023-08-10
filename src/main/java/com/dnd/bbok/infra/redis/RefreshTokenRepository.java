package com.dnd.bbok.infra.redis;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableRedisRepositories
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, UUID> {

  Optional<RefreshToken> findByRefreshToken(String refreshToken);
}

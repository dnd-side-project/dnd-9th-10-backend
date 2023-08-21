package com.dnd.bbok.member.adapter.out.persistence;

import static com.dnd.bbok.global.exception.ErrorCode.JWT_EXPIRED_TOKEN;
import static com.dnd.bbok.global.exception.ErrorCode.JWT_INVALID_TOKEN;
import static com.dnd.bbok.global.exception.ErrorCode.JWT_SIGNATURE_INVALID_TOKEN;

import com.dnd.bbok.infra.redis.RefreshToken;
import com.dnd.bbok.infra.redis.RefreshTokenRepository;

import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
import com.dnd.bbok.member.adapter.out.persistence.mapper.MemberMapper;
import com.dnd.bbok.member.adapter.out.persistence.repository.MemberRepository;
import com.dnd.bbok.member.application.exception.JwtException;
import com.dnd.bbok.member.application.port.out.JwtTokenPort;

import com.dnd.bbok.member.domain.Member;
import com.dnd.bbok.member.application.port.in.response.SessionUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class JwtTokenAdapter implements JwtTokenPort {

  private final JwtTokenInitializer jwtTokenInitializer;
  private final MemberRepository memberRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final MemberMapper memberMapper;

  @Override
  public String createAccessToken(Member member) {
    Date now = new Date();
    Date validity = new Date(now.getTime() + jwtTokenInitializer.getExpireAccess());

    return Jwts.builder()
        .signWith(jwtTokenInitializer.getKey(), SignatureAlgorithm.HS256)
        .setSubject(member.getId().toString())
        .claim("role", member.getRole().getAuthority())
        .setIssuer("bbok")
        .setIssuedAt(now)
        .setExpiration(validity)
        .compact();
  }

  @Override
  public String createRefreshToken(Member guest) {
    Date now = new Date();
    Date validity = new Date(now.getTime() + jwtTokenInitializer.getExpireRefresh());

    String refreshToken = Jwts.builder()
        .signWith(jwtTokenInitializer.getKey(), SignatureAlgorithm.HS256)
        .setIssuer("bbok")
        .setIssuedAt(now)
        .setExpiration(validity)
        .compact();

    RefreshToken token = new RefreshToken(guest.getId(), refreshToken);
    refreshTokenRepository.save(token);
    return refreshToken;
  }

  @Override
  public Authentication getAuthentication(String accessToken) {
    Claims claims = extractAllClaims(accessToken);
    String role = claims.get("role").toString();
    String uuid = claims.getSubject();
    return new UsernamePasswordAuthenticationToken(getSessionUser(uuid), "",
        getGrantedAuthorities(role));
  }

  @Override
  public boolean isTokenExpirationSafe(String refreshToken) {
    Instant expiration = extractAllClaims(refreshToken).getExpiration().toInstant();
    Instant now = Instant.now();
    return hasTokenExpMoreThanThreeDays(expiration, now);
  }

  @Override
  public void saveRefreshTokenInRedis(Member member, String refreshToken) {
    refreshTokenRepository.save(new RefreshToken(member.getId(), refreshToken));
  }

  @Override
  public void validateToken(String token) {
    try {
      Jwts.parserBuilder().setSigningKey(jwtTokenInitializer.getKey()).build().parseClaimsJws(token);
    } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
      throw new JwtException(JWT_INVALID_TOKEN);
    } catch (SignatureException e) {
      throw new JwtException(JWT_SIGNATURE_INVALID_TOKEN);
    } catch (ExpiredJwtException e) {
      throw new JwtException(JWT_EXPIRED_TOKEN);
    }
  }

  private Claims extractAllClaims(String token) {
    try {
      return Jwts.parserBuilder().setSigningKey(jwtTokenInitializer.getKey()).build()
          .parseClaimsJws(token).getBody();
    } catch (ExpiredJwtException e) {
      return e.getClaims();
    }
  }

  private SessionUser getSessionUser(String uuid) {
    UUID changeUuid = UUID.fromString(uuid);

    Optional<MemberEntity> optionalMember = memberRepository.findById(changeUuid);
    if (optionalMember.isEmpty()) {
      throw new JwtException(JWT_INVALID_TOKEN);
    }
    Member member = memberMapper.toDomain(optionalMember.get());

    return new SessionUser(member);
  }

  private static List<GrantedAuthority> getGrantedAuthorities(String role) {
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority(role));
    return grantedAuthorities;
  }

  private boolean hasTokenExpMoreThanThreeDays(Instant expiration, Instant now) {
    return (Duration.between(now, expiration).compareTo(Duration.ofDays(3)) >= 0);
  }
}

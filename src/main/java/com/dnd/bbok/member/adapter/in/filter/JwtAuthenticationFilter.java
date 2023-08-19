package com.dnd.bbok.member.adapter.in.filter;

import static com.dnd.bbok.global.exception.ErrorCode.JWT_INVALID_TOKEN;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.dnd.bbok.member.application.exception.JwtException;
import com.dnd.bbok.member.application.port.in.usecase.GetAuthQuery;
import com.dnd.bbok.member.application.port.in.usecase.TokenValidateUseCase;
import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final GetAuthQuery getAuthQuery;
  private final TokenValidateUseCase tokenValidateUseCase;

  private static final List<String> EXCLUDE_URL = List.of(
      "/api/v1/oauth/jwt/refresh", "/api/v1/account/kakao/result", "/api/v1/guest/signup");

  /**
   * 1. request header 에서 Authorization 값을 가져온다.
   *
   * 1-1. Authorization 값이 없다면? 사용자가 처음 요청
   * accessToken 발급 / 해당 유저를 저장한 후에 UUID를 발급하여 응답한다.
   *
   * 1-2. Authorization 값이 있다면? 유효성 검증
   * 토큰이 올바른지와 만료됐는지 검증한다.
   * 유효성 검증에서 실패하면 정의한 예외 처리를 해준다.
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    //사용자의 요청 헤더에서 Authorization 값을 가져온다.
    String header = request.getHeader(AUTHORIZATION);

    if(hasAuthorization(header)) {
      String accessToken = header.substring(7);
      validateToken(accessToken);
      Authentication authentication = getAuthQuery.getAuthentication(accessToken);

      //SecurityContext에 Authentication 객체를 저장한다.
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    return EXCLUDE_URL.stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
  }

  /**
   * header가 null이거나, Bearer로 시작하는지 검증한다.
   */
  private boolean hasAuthorization(String header) {
    return header != null && parseBearerToken(header);
  }

  private boolean parseBearerToken(String bearerToken) {
    // 비어 있는 경우, Bearer 로 시작하지 않는 경우, Bearer 뒤에 아무것도 없는 경우
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")
        && bearerToken.length() >= 8) {
      return true;
    }
    throw new JwtException(JWT_INVALID_TOKEN);
  }

  private void validateToken(String accessToken) {
    tokenValidateUseCase.validateToken(accessToken);
  }
}

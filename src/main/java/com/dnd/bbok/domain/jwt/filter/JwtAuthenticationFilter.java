package com.dnd.bbok.domain.jwt.filter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.dnd.bbok.domain.jwt.service.JwtTokenProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtTokenProvider jwtTokenProvider;
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

    if(validateHeader(header)) {
      // 헤더에서 JWT를 받아온다.
      String accessToken = header.substring(7);

      log.info("accessToken: {} 으로 Authentication 객체를 찾습니다.", accessToken);
      Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

      //SecurityContext에 Authentication 객체를 저장한다.
      SecurityContextHolder.getContext().setAuthentication(authentication);
      log.info("SecurityContextHolder 에 Authentication 객체를 저장했습니다. 인증 완료 {}",
          authentication.getName());
    }

    filterChain.doFilter(request, response);
  }

  /**
   * header가 null이거나, Bearer로 시작하는지 검증한다.
   */
  private boolean validateHeader(String header) {
    return header != null && header.startsWith("Bearer ");
  }

}

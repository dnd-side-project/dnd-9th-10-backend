package com.dnd.bbok.global.security;

import com.dnd.bbok.member.adapter.in.filter.JwtAuthenticationEntryPointHandler;
import com.dnd.bbok.member.adapter.in.filter.JwtAuthenticationFilter;
import com.dnd.bbok.member.adapter.in.filter.JwtExceptionFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.http.SessionCreationPolicy;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final JwtExceptionFilter jwtExceptionFilter;
  private final JwtAuthenticationEntryPointHandler jwtAuthenticationEntryPointHandler;
  private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .csrf().disable() //CSRF 보호 기능 비활성화
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http//HTTP 헤더에 사용자의 이름과 암호 포함을 비활성화
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/test", "/api/v1/member", "/api/v1/checklist", "/api/v1/character", "/api/v1//friend").authenticated() //해당 요청은 인증이 필요하다.
        .antMatchers(HttpMethod.POST, "/api/v1/friend").authenticated()
        .antMatchers(HttpMethod.PATCH, "/api/v1/friend/{id}", "/api/v1/friend/{id}/deactivate").authenticated()
        .antMatchers("/**").permitAll() //해당 요청은 누구나 다 들어올 수 있다.
        .and()
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class);

    http.exceptionHandling()
        .authenticationEntryPoint(jwtAuthenticationEntryPointHandler) // 인증 되지 않는 사용자가 접근할 경우
        .accessDeniedHandler(jwtAccessDeniedHandler); // 인증은 했으나 권한이 없는 경우

    return http.build();
  }


}

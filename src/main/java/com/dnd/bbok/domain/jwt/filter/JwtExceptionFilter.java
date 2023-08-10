package com.dnd.bbok.domain.jwt.filter;

import com.dnd.bbok.domain.jwt.exception.JwtException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dnd.bbok.global.exception.ErrorResponse;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      filterChain.doFilter(request, response);
    } catch (JwtException e) {
      response.setStatus(e.getErrorCode().getStatus());
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      objectMapper.writeValue(response.getWriter(), ErrorResponse.of(e.getErrorCode()));
    }
  }
}

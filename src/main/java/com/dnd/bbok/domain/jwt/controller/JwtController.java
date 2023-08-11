package com.dnd.bbok.domain.jwt.controller;

import static com.dnd.bbok.domain.member.service.MemberSignUpService.setCookieAndHeader;

import com.dnd.bbok.domain.jwt.dto.ReIssueTokenDto;
import com.dnd.bbok.domain.jwt.service.JwtTokenReissueService;
import com.dnd.bbok.global.response.MessageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "토큰 관련 컨트롤러")
public class JwtController {
  private final JwtTokenReissueService jwtTokenReissueService;

  @GetMapping("/api/v1/jwt/refresh")
  @ApiOperation(value = "Jwt 재발급", notes = "Jwt를 재발급 할 수 있습니다.")
  public ResponseEntity<MessageResponse> reIssueToken(@CookieValue(name = "refreshToken") String refreshToken) {
    ReIssueTokenDto reIssueTokenDto = jwtTokenReissueService.reIssueToken(refreshToken);
    HttpHeaders headers = setCookieAndHeader(reIssueTokenDto);
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.CREATED, "Token 재발급 성공"), headers, HttpStatus.CREATED);
  }

}

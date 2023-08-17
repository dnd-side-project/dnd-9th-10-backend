package com.dnd.bbok.member.adapter.in.web;

import com.dnd.bbok.global.response.MessageResponse;
import com.dnd.bbok.global.util.CookieUtil;
import com.dnd.bbok.global.util.HttpHeaderUtil;
import com.dnd.bbok.member.application.port.in.response.ReIssueToken;
import com.dnd.bbok.member.application.port.in.usecase.TokenReissueUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "토큰 관련 컨트롤러")
public class ReissueTokenController {

  private final TokenReissueUseCase tokenReissueUseCase;

  //TODO: @RequestParam이 아닌, @CookieValue로 가능하도록
  @GetMapping("/api/v1/jwt/refresh")
  @ApiOperation(value = "Jwt 재발급", notes = "Jwt를 재발급 할 수 있습니다.")
  public ResponseEntity<MessageResponse> reIssueToken(@RequestParam(name = "refreshToken") String refreshToken) {
    ReIssueToken reIssueToken = tokenReissueUseCase.reIssueToken(refreshToken);
    HttpHeaders headers = setCookieAndHeader(reIssueToken);
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.CREATED, "Token 재발급 성공"), headers, HttpStatus.CREATED);
  }

  //TODO: 토큰 재발급도 멤버 생성처럼 헤더/쿠키가 아니라 바로 response에 넣어도 되는지 클라이언트분들께 여쭤보기
  public static HttpHeaders setCookieAndHeader(ReIssueToken reIssueToken) {
    HttpHeaders headers = new HttpHeaders();
    HttpHeaderUtil.setAccessToken(headers, reIssueToken.getAccessToken());
    CookieUtil.setRefreshCookie(headers, reIssueToken.getRefreshToken());
    return headers;
  }

}

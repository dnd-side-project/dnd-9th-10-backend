package com.dnd.bbok.member.adapter.in.web;

import com.dnd.bbok.global.response.DataResponse;
import com.dnd.bbok.member.application.port.in.response.ReIssueToken;
import com.dnd.bbok.member.application.port.in.usecase.TokenReissueUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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

  @GetMapping("/api/v1/jwt/refresh")
  @ApiOperation(value = "Jwt 재발급", notes = "Jwt를 재발급 할 수 있습니다.")
  public ResponseEntity<DataResponse<ReIssueToken>> reIssueToken(@RequestParam(name = "refreshToken") String refreshToken) {
    ReIssueToken reIssueToken = tokenReissueUseCase.reIssueToken(refreshToken);
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.CREATED, "Token 재발급 성공", reIssueToken), HttpStatus.CREATED);
  }

}

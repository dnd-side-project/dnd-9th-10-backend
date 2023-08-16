package com.dnd.bbok.member.adapter.in.web;

import com.dnd.bbok.domain.jwt.dto.SessionUser;
import com.dnd.bbok.global.response.DataResponse;
import com.dnd.bbok.member.application.port.in.GetMemberInfoResponse;
import com.dnd.bbok.member.application.port.in.GetMemberQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 컨트롤러에서는 port들을 가지고 있는 서비스의 추상화 객체인 Usecase, dto를 가지고 있어야 한다.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "멤버 관련 컨트롤러")
public class GetAllMemberController {

  private final GetMemberQuery getMemberQuery;

  /**
   * 유저가 자기 자신의 정보에 대해 알 수 있다.
   */
  @ApiOperation(
      value = "내 정보 조회",
      notes = "마이 페이지에서 사용자의 정보를 볼 수 있습니다.")
  @PreAuthorize("isAuthenticated()")
  @GetMapping("/api/v1/member")
  public ResponseEntity<DataResponse<GetMemberInfoResponse>> getMember(
      @AuthenticationPrincipal SessionUser sessionUser
  ) {
    GetMemberInfoResponse memberInfo = getMemberQuery.getMember(sessionUser.getUuid());
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.OK, "멤버 정보 조회 성공", memberInfo), HttpStatus.OK);
  }

}

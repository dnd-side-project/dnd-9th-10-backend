package com.dnd.bbok.friend.adapter.in.web;

import com.dnd.bbok.friend.application.port.in.response.FriendsDto;
import com.dnd.bbok.friend.application.port.in.usecase.GetFriendsQuery;
import com.dnd.bbok.global.response.DataResponse;
import com.dnd.bbok.member.application.port.in.response.SessionUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "친구 관련 컨트롤러")
@RequiredArgsConstructor
public class GetFriendsController {

  private final GetFriendsQuery getFriendsQuery;

  @ApiOperation(value = "친구 목록 조회")
  @GetMapping("/friend")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<DataResponse<FriendsDto>> getFriends(
      @AuthenticationPrincipal SessionUser sessionUser
  ) {
    FriendsDto friends = getFriendsQuery.getFriends(sessionUser.getUuid());
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.OK, "친구 목록 조회 성공", friends), HttpStatus.OK);
  }

}

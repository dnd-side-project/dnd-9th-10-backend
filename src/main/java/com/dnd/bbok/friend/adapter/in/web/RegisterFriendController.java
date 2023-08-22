package com.dnd.bbok.friend.adapter.in.web;

import com.dnd.bbok.friend.application.port.in.request.CreateFriendRequest;
import com.dnd.bbok.friend.application.port.in.usecase.RegisterFriendUseCase;
import com.dnd.bbok.member.application.port.in.response.SessionUser;
import com.dnd.bbok.global.response.MessageResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "친구 관련 컨트롤러")
@RequiredArgsConstructor
public class RegisterFriendController {

  private final RegisterFriendUseCase registerFriendUseCase;

  @ApiOperation(value = "친구 등록")
  @PostMapping("/friend")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<MessageResponse> createFriend(
      @AuthenticationPrincipal SessionUser sessionUser,
      @RequestBody CreateFriendRequest requestFriend) {
    registerFriendUseCase.createFriendCharacter(sessionUser.getUuid(), requestFriend);
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.CREATED, "친구 등록 성공"), HttpStatus.CREATED);
  }

}

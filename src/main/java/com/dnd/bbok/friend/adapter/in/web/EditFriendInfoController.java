package com.dnd.bbok.friend.adapter.in.web;

import com.dnd.bbok.friend.application.port.in.request.UpdateFriendRequest;
import com.dnd.bbok.friend.application.port.in.usecase.EditFriendUseCase;
import com.dnd.bbok.member.application.port.in.response.SessionUser;
import com.dnd.bbok.global.response.MessageResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "친구 관련 컨트롤러")
@RequiredArgsConstructor
public class EditFriendInfoController {

  private final EditFriendUseCase editFriendUseCase;

  @ApiOperation(value = "친구 정보 수정")
  @PatchMapping("/friend/{id}")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<MessageResponse> getBbokCharacter(
      @Parameter(name = "id", in = ParameterIn.PATH, description = "친구 id") @PathVariable("id") Long friendId,
      @RequestBody UpdateFriendRequest updateFriendRequest,
      @AuthenticationPrincipal SessionUser sessionUser
  ) {
    editFriendUseCase.editName(sessionUser.getUuid() ,friendId, updateFriendRequest);
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.OK, "친구 이름 수정 성공"), HttpStatus.OK);
  }

  @ApiOperation(value = "친구 관계 정리")
  @PatchMapping("/friend/{id}/deactivate")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<MessageResponse> getBbokCharacter(
      @Parameter(name = "id", in = ParameterIn.PATH, description = "친구 id") @PathVariable("id") Long friendId,
      @AuthenticationPrincipal SessionUser sessionUser
  ) {
    editFriendUseCase.editStatus(sessionUser.getUuid(), friendId);
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.OK, "친구와의 관계 정리 성공"), HttpStatus.OK);
  }

}

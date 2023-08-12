package com.dnd.bbok.domain.friend.controller;

import com.dnd.bbok.domain.friend.dto.request.FriendRequestDto;
import com.dnd.bbok.domain.friend.dto.response.BbokCharactersDto;
import com.dnd.bbok.domain.friend.service.MemberFriendUseCaseService;
import com.dnd.bbok.domain.friend.service.IconService;
import com.dnd.bbok.domain.jwt.dto.SessionUser;
import com.dnd.bbok.global.response.DataResponse;
import com.dnd.bbok.global.response.MessageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "친구 관련 컨트롤러")
@RequiredArgsConstructor
public class FriendController {

  private final IconService iconService;
  private final MemberFriendUseCaseService memberFriendUseCaseService;

  @ApiOperation(value = "캐릭터 정보 제공")
  @GetMapping("/character")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<DataResponse<BbokCharactersDto>> getBbokCharacter() {
    BbokCharactersDto characters = iconService.getCharacterIcon();
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.OK, "캐릭터 목록 제공 성공", characters), HttpStatus.OK);
  }

  @ApiOperation(value = "친구 등록")
  @PostMapping("/friend")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<MessageResponse> createFriend(
      @AuthenticationPrincipal SessionUser sessionUser,
      @RequestBody FriendRequestDto requestFriend) {
    memberFriendUseCaseService.createFriendCharacter(sessionUser.getUuid(), requestFriend);
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.CREATED, "친구 등록 성공"), HttpStatus.CREATED);
  }

}
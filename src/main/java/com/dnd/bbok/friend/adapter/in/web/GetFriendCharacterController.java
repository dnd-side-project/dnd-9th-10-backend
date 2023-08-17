package com.dnd.bbok.friend.adapter.in.web;

import com.dnd.bbok.domain.friend.dto.response.BbokCharactersDto;
import com.dnd.bbok.friend.application.port.in.usecase.GetIconQuery;
import com.dnd.bbok.global.response.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "친구 관련 컨트롤러")
@RequiredArgsConstructor
public class GetFriendCharacterController {

  private final GetIconQuery getIconQuery;

  @ApiOperation(value = "캐릭터 정보 제공")
  @GetMapping("/character")
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<DataResponse<BbokCharactersDto>> getBbokCharacter() {
    BbokCharactersDto characters = getIconQuery.getCharacterIcon();
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.OK, "캐릭터 목록 제공 성공", characters), HttpStatus.OK);
  }

}

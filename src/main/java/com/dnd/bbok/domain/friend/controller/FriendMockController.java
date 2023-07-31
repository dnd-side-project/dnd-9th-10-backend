package com.dnd.bbok.domain.friend.controller;

import com.dnd.bbok.domain.friend.dto.response.FriendsDto;
import com.dnd.bbok.global.response.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/friend")
@Api(tags = "Mock 친구 관련 컨트롤러")
public class FriendMockController {

  @ApiOperation(value = "친구 목록 조회")
  @GetMapping
  public ResponseEntity<DataResponse<FriendsDto>> getFriends() {
    FriendsDto friends = new FriendsDto();
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.OK, "친구 목록 조회 성공", friends), HttpStatus.OK);
  }

}

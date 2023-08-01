package com.dnd.bbok.domain.friend.controller;

import com.dnd.bbok.domain.checklist.dto.response.MyChecklistDto;
import com.dnd.bbok.domain.friend.dto.request.FriendRequestDto;
import com.dnd.bbok.domain.friend.dto.response.BbokCharactersDto;
import com.dnd.bbok.domain.friend.dto.response.FriendsDto;
import com.dnd.bbok.global.response.DataResponse;
import com.dnd.bbok.global.response.MessageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@Api(tags = "Mock 친구 관련 컨트롤러")
public class FriendMockController {

  @ApiOperation(value = "캐릭터 정보 제공")
  @GetMapping("/character")
  public ResponseEntity<DataResponse<BbokCharactersDto>> getBbokCharacter() {
    BbokCharactersDto characters = new BbokCharactersDto();
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.OK, "캐릭터 목록 제공 성공", characters), HttpStatus.OK);
  }

  @ApiOperation(value = "친구 목록 조회")
  @GetMapping("/friend")
  public ResponseEntity<DataResponse<FriendsDto>> getFriends() {
    FriendsDto friends = new FriendsDto();
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.OK, "친구 목록 조회 성공", friends), HttpStatus.OK);
  }

  @ApiOperation(value = "친구 등록")
  @PostMapping("/friend")
  public ResponseEntity<MessageResponse> createFriend(
      @RequestBody FriendRequestDto requestFriend) {
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.CREATED, "친구 등록 성공"), HttpStatus.CREATED);
  }

  @ApiOperation(value = "나만의 기준 조회")
  @GetMapping("/friend/checklist")
  public ResponseEntity<DataResponse<MyChecklistDto>> getChecklist() {
    MyChecklistDto myChecklist = new MyChecklistDto();
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.OK, "기준 조회 성공", myChecklist), HttpStatus.OK);
  }

}

package com.dnd.bbok.mock;

import com.dnd.bbok.global.response.MessageResponse;
import com.dnd.bbok.member.application.port.in.request.EditMemberChecklistRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Mock 친구 관련 컨트롤러")
public class FriendMockController {

  @ApiOperation(value = "나만의 기준 수정")
  @PatchMapping("/friend/checklist")
  public ResponseEntity<MessageResponse> updateChecklist(
      @RequestBody EditMemberChecklistRequest requestChecklist
  ){
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.OK, "기준 정보 수정 성공"), HttpStatus.OK
    );
  }
}

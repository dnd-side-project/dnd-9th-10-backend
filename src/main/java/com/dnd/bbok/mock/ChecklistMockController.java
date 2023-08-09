package com.dnd.bbok.mock;

import com.dnd.bbok.domain.checklist.dto.request.MemberChecklistRequestDto;
import com.dnd.bbok.domain.checklist.dto.response.BasicChecklistDto;
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
@RequestMapping("/api/v1/checklist")
@Api(tags = "Mock 체크리스트 관련 컨트롤러")
public class ChecklistMockController {

  @ApiOperation(value = "기본 체크리스트 제공")
  @GetMapping("")
  public ResponseEntity<DataResponse<BasicChecklistDto>> getBasicChecklist() {
    BasicChecklistDto basicChecklist = new BasicChecklistDto();
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.OK, "기본 체크리스트 제공 성공", basicChecklist), HttpStatus.OK);
  }

  @ApiOperation(value = "사용자 정의 체크리스트 추가")
  @PostMapping("")
  public ResponseEntity<MessageResponse> createChecklist(
      @RequestBody MemberChecklistRequestDto memberChecklist
  ) {
    return new ResponseEntity<>(
        MessageResponse.of(HttpStatus.CREATED, "체크리스트 추가 성공"), HttpStatus.CREATED);
  }


}

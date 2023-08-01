package com.dnd.bbok.domain.checklist.controller;

import com.dnd.bbok.domain.checklist.dto.response.BasicChecklistDto;
import com.dnd.bbok.global.response.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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


}

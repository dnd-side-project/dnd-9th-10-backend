package com.dnd.bbok.domain.diary.controller;

import com.dnd.bbok.domain.diary.dto.response.DiaryDto;
import com.dnd.bbok.global.response.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/friend")
@Api(tags = "Mock 일기 관련 컨트롤러")
public class DiaryMockController {

    @ApiOperation(value = "일화 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<DiaryDto>> getDiary(@PathVariable("id") int id) {
        DiaryDto diary = new DiaryDto(id);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "일화 조회 성공", diary), HttpStatus.OK);
    }
}

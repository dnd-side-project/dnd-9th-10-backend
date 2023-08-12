package com.dnd.bbok.domain.diary.controller;

import com.dnd.bbok.domain.diary.dto.request.DiaryRequestDto;
import com.dnd.bbok.domain.diary.dto.response.DiaryCreateDto;
import com.dnd.bbok.global.response.DataResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/friend")
@Api(tags = "일기 관련 컨트롤러")
public class DiaryController {
    @ApiOperation(value = "일기 등록")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("{id}/diary")
    public ResponseEntity<DataResponse<DiaryCreateDto>> createDiary(
            @Parameter(name = "id", in = ParameterIn.PATH, description = "친구 id") @PathVariable("id") Long id,
            @RequestBody DiaryRequestDto diaryRequestDto) {
        DiaryCreateDto createDiaryDto = new DiaryCreateDto();
        // 1. 새로운 태그 생성 필요 여부 확인 및 생성

        // 2. 일기 생성
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK , "일기 생성 성공", createDiaryDto), HttpStatus.OK);
    }
}

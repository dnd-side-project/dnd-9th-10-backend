package com.dnd.bbok.domain.diary.controller;

import com.dnd.bbok.domain.diary.dto.request.DiaryRequestDto;
import com.dnd.bbok.domain.diary.dto.response.*;
import com.dnd.bbok.domain.tag.entity.DiaryTag;
import com.dnd.bbok.global.response.DataResponse;
import com.dnd.bbok.global.response.MessageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/friend")
@Api(tags = "Mock 일기 관련 컨트롤러")
public class DiaryMockController {

    @ApiOperation(value = "일기 상세 조회")
    @GetMapping("/diary/detail/{id}")
    public ResponseEntity<DataResponse<DiaryDto>> getDiary(@PathVariable("id") int id) {
        DiaryDto diary = new DiaryDto(id);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "일기 조회 성공", diary), HttpStatus.OK);
    }

    @ApiOperation(value = "일기 목록 조회")
    @GetMapping("{id}/diary")
    public ResponseEntity<DataResponse<DiaryListDto>> getDiaryList(
            @Parameter(name = "id", in = ParameterIn.PATH, description = "친구 id") @PathVariable("id") int id,
            @Parameter(name = "offset", in = ParameterIn.QUERY, description = "목록 오프셋") @RequestParam(value = "offset", required = false) Integer offset,
            @Parameter(name = "order", in = ParameterIn.QUERY, description = "시간 정렬 기준") @RequestParam(value = "order", required = false) String order,
            @Parameter(name = "q", in = ParameterIn.QUERY, description = "검색어") @RequestParam(value = "q", required = false) String keyword,
            @Parameter(name = "tag", in = ParameterIn.QUERY, description = "태그") @RequestParam(value = "tag", required = false) String tag
    ) {
        DiaryDto diary = new DiaryDto(1);
        ArrayList<DiaryDto> diaries = new ArrayList<>();
        diaries.add(diary);
        DiaryListDto list = new DiaryListDto(diaries);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "일기 목록 조회 성공", list), HttpStatus.OK);
    }

    @ApiOperation(value = "일기 등록")
    @PostMapping("{id}/diary")
    public ResponseEntity<DataResponse<DiaryCreateDto>> createDiary(
            @Parameter(name = "id", in = ParameterIn.PATH, description = "친구 id") @PathVariable("id") int id,
            @RequestBody DiaryRequestDto diaryRequestDto) {
        DiaryCreateDto createDiaryDto = new DiaryCreateDto();
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK , "일기 생성 성공", createDiaryDto), HttpStatus.OK);
    }

    @ApiOperation("태그 목록 조회")
    @GetMapping("diary/tag")
    public ResponseEntity<DataResponse<DiaryTagDto>> getTagList() {
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK , "태그 목록 조회 성공", new DiaryTagDto()), HttpStatus.OK);
    }

    @ApiOperation("스티커 목록 조회")
    @GetMapping("diary/sticker")
    public ResponseEntity<DataResponse<DiaryStickerDto>> getStickerList() {
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "스티커 목록 조회 성공", new DiaryStickerDto()), HttpStatus.OK);
    }

    @ApiOperation("일기 수정")
    @PatchMapping("diary/{id}")
    public ResponseEntity<MessageResponse> updateDiary(
            @Parameter(name = "id", in = ParameterIn.PATH, description = "일기 id") @PathVariable("id") int id,
            @RequestBody DiaryCreateDto diaryCreateDto
    ) {
        return new ResponseEntity<>(MessageResponse.of(HttpStatus.OK, "일기 수정 성공"), HttpStatus.OK);
    }

    @ApiOperation("일기 삭제")
    @DeleteMapping("diary/{id}")
    public ResponseEntity<MessageResponse> deleteDiary(@Parameter(name = "id", in = ParameterIn.PATH, description = "일기 id") @PathVariable("id") int id) {
        return new ResponseEntity<>(MessageResponse.of(HttpStatus.OK, "일기 삭제 성공"), HttpStatus.OK);
    }
}

package com.dnd.bbok.domain.diary.controller;

import com.dnd.bbok.diary.application.port.in.response.GetDiariesResponse;
import com.dnd.bbok.diary.application.port.in.response.GetDiaryResponse;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryChecklistEntity;
import com.dnd.bbok.domain.checklist.service.DiaryChecklistService;
import com.dnd.bbok.domain.diary.dto.response.*;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
import com.dnd.bbok.domain.diary.service.DiaryService;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryTagEntity;
import com.dnd.bbok.domain.tag.service.TagService;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendTagEntity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/friend")
@Api(tags = "일기 관련 컨트롤러")
public class DiaryController {
    private final TagService tagService;
    private final DiaryService diaryService;
    private final DiaryChecklistService diaryChecklistService;

    @ApiOperation(value = "일기 목록 조회")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}/diary")
    public ResponseEntity<DataResponse<GetDiariesResponse>> getDiaries(
            @Parameter(name = "id", in = ParameterIn.PATH, description = "친구 id") @PathVariable("id") Long id,
            @Parameter(name = "offset", in = ParameterIn.QUERY, description = "목록 오프셋") @RequestParam(value = "offset", required = false) Integer offset,
            @Parameter(name = "order", in = ParameterIn.QUERY, description = "시간 정렬 기준") @RequestParam(value = "order", required = false) String order,
            @Parameter(name = "q", in = ParameterIn.QUERY, description = "검색어") @RequestParam(value = "q", required = false) String keyword,
            @Parameter(name = "tag", in = ParameterIn.QUERY, description = "태그") @RequestParam(value = "tag", required = false) String tag
    ) {
        // TODO 남의 일기를 조회 요청한 경우 에러처러??

        List<DiaryEntity> diaries = this.diaryService.getDiariesByFriendId(id);
        List<Long> diaryIds = diaries.stream().map(DiaryEntity::getId).collect(Collectors.toList());
        List<DiaryTagEntity> diaryTagEntities = this.tagService.getDiariesTags(diaryIds);
        List<DiaryChecklistEntity> diaryChecklistEntities = this.diaryChecklistService.getDiaryChecklistByDiaryIds(diaryIds);
        ArrayList<GetDiaryResponse> getDiaryRespons = new ArrayList<>();
        diaries.forEach(diary -> getDiaryRespons.add(new GetDiaryResponse(diary, diaryTagEntities, diaryChecklistEntities)));
        GetDiariesResponse list = new GetDiariesResponse(getDiaryRespons);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "일기 목록 조회 성공", list), HttpStatus.OK);
    }

    @ApiOperation("태그 목록 조회")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}/diary/tag")
    public ResponseEntity<DataResponse<DiaryTagDto>> getTags(
            @Parameter(name = "id", in = ParameterIn.PATH, description = "친구 id") @PathVariable("id") Long id
    ) {
        List<FriendTagEntity> tags = this.tagService.getFriendTagsByFriendId(id);
        DiaryTagDto diaryTagDto = new DiaryTagDto(tags);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK , "태그 목록 조회 성공", diaryTagDto), HttpStatus.OK);
    }
}

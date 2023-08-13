package com.dnd.bbok.domain.diary.controller;

import com.dnd.bbok.domain.checklist.entity.DiaryChecklist;
import com.dnd.bbok.domain.checklist.service.DiaryChecklistService;
import com.dnd.bbok.domain.diary.dto.request.DiaryRequestDto;
import com.dnd.bbok.domain.diary.dto.response.*;
import com.dnd.bbok.domain.diary.entity.Diary;
import com.dnd.bbok.domain.diary.service.DiaryService;
import com.dnd.bbok.domain.jwt.dto.SessionUser;
import com.dnd.bbok.domain.saying.service.SayingService;
import com.dnd.bbok.domain.tag.entity.DiaryTag;
import com.dnd.bbok.domain.diary.service.FriendTempService;
import com.dnd.bbok.domain.tag.service.TagService;
import com.dnd.bbok.domain.friend.entity.Friend;
import com.dnd.bbok.domain.tag.entity.FriendTag;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final FriendTempService friendService;
    private final TagService tagService;
    private final DiaryService diaryService;
    private final DiaryChecklistService diaryChecklistService;
    private final SayingService sayingService;

    @ApiOperation(value = "일기 등록")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("{id}/diary")
    public ResponseEntity<DataResponse<DiaryCreateDto>> createDiary(
            @Parameter(name = "id", in = ParameterIn.PATH, description = "친구 id") @PathVariable("id") Long id,
            @RequestBody DiaryRequestDto diaryRequestDto,
            @AuthenticationPrincipal SessionUser sessionUser
    ) {

        Friend friend = friendService.getFriend(id);
        // 1. 새로운 친구 태그 엔티티 생성 필요 여부 확인 및 생성
        List<FriendTag> friendTags = tagService.getFriendTags(friend, diaryRequestDto.getTags());
        // 2. 일기 생성
        Diary diary = diaryService.createDiary(friend, diaryRequestDto);
        // 3. 일기 태그 엔티티 생성
        tagService.createDiaryTag(diary, friendTags);
        // 3. 체크리스트 생성
        diaryChecklistService.createDiaryChecklist(diary, diaryRequestDto.getChecklist());
        // 4. 점수 계산 및 업데이트
        Long score = diaryService.updateFriendScore(friend, diaryRequestDto.getChecklist());
        // 5. 랜덤 명언 조회
        DiarySayingDto diarySayingDto = sayingService.getRandomSaying(sessionUser.getUuid());

        DiaryCreateDto createDiaryDto = new DiaryCreateDto(diarySayingDto, score);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK , "일기 생성 성공", createDiaryDto), HttpStatus.OK);
    }

    @ApiOperation(value = "일기 목록 조회")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}/diary")
    public ResponseEntity<DataResponse<DiariesDto>> getDiaries(
            @Parameter(name = "id", in = ParameterIn.PATH, description = "친구 id") @PathVariable("id") Long id,
            @Parameter(name = "offset", in = ParameterIn.QUERY, description = "목록 오프셋") @RequestParam(value = "offset", required = false) Integer offset,
            @Parameter(name = "order", in = ParameterIn.QUERY, description = "시간 정렬 기준") @RequestParam(value = "order", required = false) String order,
            @Parameter(name = "q", in = ParameterIn.QUERY, description = "검색어") @RequestParam(value = "q", required = false) String keyword,
            @Parameter(name = "tag", in = ParameterIn.QUERY, description = "태그") @RequestParam(value = "tag", required = false) String tag
    ) {
        // TODO 남의 일기를 조회 요청한 경우 에러처러??

        List<Diary> diaries = this.diaryService.getDiariesByFriendId(id);
        List<Long> diaryIds = diaries.stream().map(Diary::getId).collect(Collectors.toList());
        List<DiaryTag> diaryTags = this.tagService.getDiariesTags(diaryIds);
        List<DiaryChecklist> diaryChecklists = this.diaryChecklistService.getDiaryChecklistByDiaryIds(diaryIds);
        ArrayList<DiaryDto> diaryDtos = new ArrayList<>();
        diaries.forEach(diary -> diaryDtos.add(new DiaryDto(diary, diaryTags, diaryChecklists)));
        DiariesDto list = new DiariesDto(diaryDtos);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK, "일기 목록 조회 성공", list), HttpStatus.OK);
    }

    @ApiOperation("태그 목록 조회")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("{id}/diary/tag")
    public ResponseEntity<DataResponse<DiaryTagDto>> getTags(
            @Parameter(name = "id", in = ParameterIn.PATH, description = "친구 id") @PathVariable("id") Long id
    ) {
        List<FriendTag> tags = this.tagService.getFriendTagsByFriendId(id);
        DiaryTagDto diaryTagDto = new DiaryTagDto(tags);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK , "태그 목록 조회 성공", diaryTagDto), HttpStatus.OK);
    }
}

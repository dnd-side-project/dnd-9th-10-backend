package com.dnd.bbok.domain.diary.controller;

import com.dnd.bbok.domain.checklist.service.DiaryChecklistService;
import com.dnd.bbok.domain.diary.dto.request.DiaryRequestDto;
import com.dnd.bbok.domain.diary.dto.response.DiaryCreateDto;
import com.dnd.bbok.domain.diary.dto.response.DiarySayingDto;
import com.dnd.bbok.domain.diary.entity.Diary;
import com.dnd.bbok.domain.diary.service.DiaryService;
import com.dnd.bbok.domain.jwt.dto.SessionUser;
import com.dnd.bbok.domain.saying.service.SayingService;
import com.dnd.bbok.domain.tag.service.DiaryTagEntityService;
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

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/friend")
@Api(tags = "일기 관련 컨트롤러")
public class DiaryController {
    private final FriendTempService friendService;
    private final TagService tagService;
    private final DiaryService diaryService;
    private final DiaryTagEntityService diaryTagEntityService;
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
        diaryTagEntityService.createDiaryTag(diary, friendTags);
        // 3. 체크리스트 생성
        diaryChecklistService.createDiaryChecklist(diary, diaryRequestDto.getChecklist());
        // 4. 점수 계산 및 업데이트
        Long score = diaryService.updateFriendScore(friend, diaryRequestDto.getChecklist());
        // 5. 랜덤 명언 조회
        DiarySayingDto diarySayingDto = sayingService.getRandomSaying(sessionUser.getUuid());

        DiaryCreateDto createDiaryDto = new DiaryCreateDto(diarySayingDto, score);
        return new ResponseEntity<>(DataResponse.of(HttpStatus.OK , "일기 생성 성공", createDiaryDto), HttpStatus.OK);
    }
}

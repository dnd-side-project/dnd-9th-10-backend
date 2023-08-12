package com.dnd.bbok.domain.diary.service;

import com.dnd.bbok.domain.diary.dto.request.ChecklistDto;
import com.dnd.bbok.domain.diary.dto.request.DiaryRequestDto;
import com.dnd.bbok.domain.diary.entity.Diary;
import com.dnd.bbok.domain.diary.repository.DiaryRepository;
import com.dnd.bbok.domain.friend.entity.Friend;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 일기 생성 및 조회 Logic 을 구현한 Service
 */
@Service
@RequiredArgsConstructor
public class DiaryService {
    private final static int MAX_CHECKLIST = 5;
    private final static double PREV_SCORE_WEIGHT = 0.9;
    private final static int BAD_CHECK_WEIGHT = 4;
    private final static int BAD_UNCHECK_WEIGHT = 1;
    private final static int GOOD_CHECK_WEIGHT = 2;

    private final DiaryEntityService diaryEntityService;
    private final FriendTempService friendTempService;

    public Diary createDiary(Friend friend, DiaryRequestDto diaryRequestDto) {
        return this.diaryEntityService.createDiary(friend, diaryRequestDto);
    }

    public Long updateFriendScore(Friend friend, List<ChecklistDto> checklistDtos) {
        // 점수 계산
        // 이전 score * 0.9 + (부정 체크 * 4 - (부정 체크 안한 갯수 * 1 + 긍정 체크 * 2))

        long score = (long) (friend.getFriendScore() * PREV_SCORE_WEIGHT);

        long badCheckCount = checklistDtos.stream().filter(ele -> ele.getIsChecked() && !ele.getIsGood()).count();
        long goodCheckCount = checklistDtos.stream().filter(ele -> ele.getIsChecked() && ele.getIsGood()).count();

        score += badCheckCount * BAD_CHECK_WEIGHT - ((MAX_CHECKLIST - badCheckCount) * BAD_UNCHECK_WEIGHT + goodCheckCount * GOOD_CHECK_WEIGHT);

        return friendTempService.updateFriendScore(friend, score).getFriendScore();
    }

}

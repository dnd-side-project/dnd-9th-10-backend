package com.dnd.bbok.diary.application.service;

import com.dnd.bbok.diary.application.port.in.request.CreateDiaryRequest;
import com.dnd.bbok.diary.application.port.in.request.UpdateDiaryRequest;
import com.dnd.bbok.diary.application.port.in.response.CreateDiaryResponse;
import com.dnd.bbok.diary.application.port.in.usecase.UpdateDiaryUseCase;
import com.dnd.bbok.diary.application.port.out.LoadDiaryPort;
import com.dnd.bbok.diary.domain.DiarySaying;
import com.dnd.bbok.diary.application.port.in.usecase.CreateDiaryUseCase;
import com.dnd.bbok.diary.application.port.out.SaveDiaryPort;
import com.dnd.bbok.diary.domain.Diary;
import com.dnd.bbok.diary.domain.DiaryChecklist;
import com.dnd.bbok.diary.domain.Tag;
import com.dnd.bbok.friend.application.port.out.LoadFriendPort;
import com.dnd.bbok.friend.application.port.out.LoadFriendTagPort;
import com.dnd.bbok.friend.application.port.out.UpdateFriendPort;
import com.dnd.bbok.friend.domain.Friend;
import com.dnd.bbok.friend.domain.FriendTag;
import com.dnd.bbok.global.exception.BusinessException;
import com.dnd.bbok.saying.application.port.out.LoadBookmarkPort;
import com.dnd.bbok.saying.application.port.out.LoadSayingPort;
import com.dnd.bbok.saying.domain.Saying;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.dnd.bbok.global.exception.ErrorCode.EXCEED_MAX_TAG_COUNT;

@Slf4j
@Service
@RequiredArgsConstructor
public class WriteDiaryService implements CreateDiaryUseCase, UpdateDiaryUseCase {
    private static final int MAX_TAG_COUNT = 7;

    private final LoadFriendTagPort loadFriendTagPort;
    private final LoadDiaryPort loadDiaryPort;
    private final LoadSayingPort loadSayingPort;
    private final LoadBookmarkPort loadBookmarkPort;
    private final LoadFriendPort loadFriendPort;

    private final SaveDiaryPort saveDiaryPort;
    private final UpdateFriendPort updateFriendPort;


    @Override
    public CreateDiaryResponse createDiary(UUID memberId, Long friendId, CreateDiaryRequest createDiaryRequest) {
        // 1. 일곱 개 이상의 태그를 사용하려 했다면 에러
        List<Tag> tags = null;
        if (createDiaryRequest.getTags() != null) {
            tags = checkFriendTagCount(friendId, createDiaryRequest.getTags());
        }

        // 2. 체크리스트 도메인 생성 및 점수 계산
        List<DiaryChecklist> checklist = null;
        Integer diaryScore = 0;
        Long friendScore = null;
        if (createDiaryRequest.getChecklist() != null) {
            checklist = createDiaryRequest.getChecklist().stream().map(
                    ele -> new DiaryChecklist(null, ele.getIsGood(), ele.getIsChecked(), ele.getId())).collect(Collectors.toList());

            Friend friend = loadFriendPort.loadByFriendId(friendId);
            diaryScore = calculateDiaryScore(checklist);
            friendScore = calculateFriendScore(friend.getFriendScore(), diaryScore);
            updateFriendPort.updateFriendScore(friend, friendScore);
        }


        // 3. 다이어리 도메인 생성 및 저장
        Diary diary = new Diary(null,
                friendId,
                createDiaryRequest.getEmoji(),
                createDiaryRequest.getContent(),
                createDiaryRequest.getDate(),
                createDiaryRequest.getSticker(),
                diaryScore,
                false,
                tags,
                checklist);
        saveDiaryPort.createDiary(friendId, diary);



        // 4. 랜덤 명언 조회
        DiarySaying diarySaying = getRandomSaying(memberId);

        return new CreateDiaryResponse(diarySaying, friendScore);
    }

    @Override
    public void updateDiary(UUID memberId, Long diaryId, UpdateDiaryRequest updateDiaryRequest) {
        Diary diary = loadDiaryPort.loadDiary(diaryId);
        Friend friend = loadFriendPort.loadByFriendId(diary.getFriendId());

        // 1. 수정하며 일곱 개 이상의 태그를 사용하려 했다면 에러
        diary.setTags(checkFriendTagCount(diary.getFriendId(), updateDiaryRequest.getTags()));

        // 2. 체크리스트 확인 및 점수 계산
        if (updateDiaryRequest.getChecklist() != null) {
            diary.setDiaryChecklist(updateDiaryRequest.getChecklist().stream().map(
                    ele -> new DiaryChecklist(null, ele.getId(), ele.getIsChecked(), null, diaryId, ele.getIsGood())).collect(Collectors.toList()));
            diary.setDiaryScore(calculateDiaryScore(diary.getDiaryChecklist()));
        } else {
            diary.setDiaryChecklist(new ArrayList<>());
            diary.setDiaryScore(0);
        }

        // 3. 일기 업데이트
        diary.setEmoji(updateDiaryRequest.getEmoji());
        diary.setContents(updateDiaryRequest.getContent());
        diary.setDiaryDate(updateDiaryRequest.getDate());
        diary.setSticker(updateDiaryRequest.getSticker());
        saveDiaryPort.saveDiary(diary);

        // 4. 전체 점수 다시 계산
        // 새로 추가되는 일기가 아니라 이전 일기가 수정되므로, 가중치 계산을 위해서는 처음부터 다시 계산해야함
        // 다만 체크리스트 수정 없이 수정하는 경우 등은 있을 수 있으므로 반드시 필요한 로직은 아님
        // 수정 여부를 직접 이전 체크리스트와 비교해가며 처리하는것보다 그냥 매번 해주는게 편할것같아서 이렇게 처리
        // 가능하다면 클라이언트에게 체크리스트를 수정했는지 여부를 표시하는 데이터를 받을 수 있다면 개선할 수 있을 것 같음
        List<Diary> diaries = loadDiaryPort.loadDiariesByFriendId(diary.getFriendId());
        long friendScore = 0L;
        for (Diary current : diaries) {
            friendScore = (long) (friendScore * 0.9) + current.getDiaryScore();
        }
        updateFriendPort.updateFriendScore(friend, friendScore);
    }

    private List<Tag> checkFriendTagCount(Long friendId, List<String> usedTags) {
        List<Tag> tags = new ArrayList<>();
        List<FriendTag> friendTags = loadFriendTagPort.loadFriendTag(friendId);
        AtomicInteger count = new AtomicInteger(friendTags.size());
        log.info("친구 태그 갯수: " + count.get());
        usedTags.forEach(tag -> {
            Optional<FriendTag> friendTag = friendTags.stream().filter(ele -> Objects.equals(ele.getName(), tag)).findFirst();
            if (friendTag.isEmpty()) {
                if (count.get() >= MAX_TAG_COUNT) {
                    throw new BusinessException(EXCEED_MAX_TAG_COUNT);
                }
                log.info("친구 태그 없음!!");
                count.addAndGet(1);
                tags.add(new Tag(tag));
            } else {
                tags.add(new Tag(friendTag.get().getId(), friendTag.get().getName()));
            }
        });

        return tags;
    }

    private final static int MAX_CHECKLIST = 5;
    private final static double PREV_SCORE_WEIGHT = 0.9;
    private final static int BAD_CHECK_WEIGHT = 4;
    private final static int BAD_UNCHECK_WEIGHT = 1;
    private final static int GOOD_CHECK_WEIGHT = 2;
    private Integer calculateDiaryScore(List<DiaryChecklist> checklists) {
        // 일기 점수 = (부정 체크 * 4 - (부정 체크 안한 갯수 * 1 + 긍정 체크 * 2))
        log.info(String.valueOf(checklists.size()));
        checklists.forEach(ele -> {
            log.info(ele.getIsGood() + " " + ele.getIsChecked());
        });
        long badCheckCount = checklists.stream().filter(ele -> ele.getIsChecked() && !ele.getIsGood()).count();
        long goodCheckCount = checklists.stream().filter(ele -> ele.getIsChecked() && ele.getIsGood()).count();
        return Math.toIntExact(BAD_CHECK_WEIGHT * badCheckCount - ((MAX_CHECKLIST - badCheckCount) * BAD_UNCHECK_WEIGHT + goodCheckCount * GOOD_CHECK_WEIGHT));
    }

    private Long calculateFriendScore(Long prevScore, Integer diaryScore) {
        // 친구 점수 = 이전 score * 0.9 + 일기 점수
        long score = (long) (prevScore * PREV_SCORE_WEIGHT) + diaryScore;
        if (score < 0) {
            // 점수는 0 ~ 100 점 사이이므로 초과된 경우 극값으로 고정해줌
            score = 0;
        } else if (score > 100) {
            score = 100;
        }
        return score;
    }

    private DiarySaying getRandomSaying(UUID memberId) {
        List<Saying> sayings = loadSayingPort.getAllSaying();
        Random random = new Random();
        int index = random.nextInt(sayings.size());
        Saying saying = sayings.get(index);
        boolean isMarked = loadBookmarkPort.isMarked(memberId, saying.getId());
        return new DiarySaying(saying.getId(), saying.getContents(), saying.getReference(), isMarked);
    }
}

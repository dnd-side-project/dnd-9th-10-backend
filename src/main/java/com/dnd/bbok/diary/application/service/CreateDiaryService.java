package com.dnd.bbok.diary.application.service;

import com.dnd.bbok.diary.application.port.in.request.CreateDiaryRequest;
import com.dnd.bbok.diary.application.port.in.response.CreateDiaryResponse;
import com.dnd.bbok.diary.application.port.in.response.DiarySaying;
import com.dnd.bbok.diary.application.port.in.usecase.CreateDiaryUseCase;
import com.dnd.bbok.diary.application.port.out.SaveDiaryPort;
import com.dnd.bbok.diary.domain.Diary;
import com.dnd.bbok.diary.domain.DiaryChecklist;
import com.dnd.bbok.diary.domain.Tag;
import com.dnd.bbok.friend.application.port.out.LoadFriendTagPort;
import com.dnd.bbok.friend.domain.FriendTag;
import com.dnd.bbok.global.exception.BusinessException;
import com.dnd.bbok.saying.application.port.out.LoadBookmarkPort;
import com.dnd.bbok.saying.application.port.out.LoadSayingPort;
import com.dnd.bbok.saying.domain.Saying;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.dnd.bbok.global.exception.ErrorCode.EXCEED_MAX_TAG_COUNT;

@Service
@RequiredArgsConstructor
public class CreateDiaryService implements CreateDiaryUseCase {
    private static final int MAX_TAG_COUNT = 5;

    private final LoadFriendTagPort loadFriendTagPort;
    private final SaveDiaryPort saveDiaryPort;
    private final LoadSayingPort loadSayingPort;
    private final LoadBookmarkPort loadBookmarkPort;


    @Override
    public CreateDiaryResponse createDiary(UUID memberId, Long friendId, CreateDiaryRequest createDiaryRequest) {
        // 1. 다섯 개 이상의 태그를 사용하려 했다면 에러
        List<Tag> tags = checkFriendTagCount(friendId, createDiaryRequest.getTags());

        // 2. 일기 생성 (Friend Tag, Diary, Diary Tag, Diary Checklist Entity)
        List<DiaryChecklist> checklist = createDiaryRequest.getChecklist().stream().map(ele -> new DiaryChecklist(null, ele.getIsChecked(), ele.getId())).collect(Collectors.toList());
        Diary diary = new Diary(null, createDiaryRequest.getEmoji(), createDiaryRequest.getContent(), createDiaryRequest.getDate(), createDiaryRequest.getSticker(), tags, checklist);
        saveDiaryPort.createDiary(friendId, diary);

        // 3. TODO 점수 계산 및 업데이트
        // Friend friend = loadFriendPort.load(friend);
        Long score = calculateScore(Math.abs(new Random().nextLong() % 100), createDiaryRequest.getChecklist());
        // friend.score = score;
        // saveFriendPort.save(friend)

        // 4. 랜덤 명언 조회
        DiarySaying diarySaying = getRandomSaying(memberId);

        return new CreateDiaryResponse(diarySaying, score);
    }

    private List<Tag> checkFriendTagCount(Long friendId, List<String> usedTags) {
        List<Tag> tags = new ArrayList<>();
        List<FriendTag> friendTags = loadFriendTagPort.loadFriendTag(friendId);
        AtomicInteger count = new AtomicInteger(friendTags.size());
        usedTags.forEach(tag -> {
            Optional<FriendTag> friendTag = friendTags.stream().filter(ele -> Objects.equals(ele.getName(), tag)).findFirst();
            if (friendTag.isEmpty()) {
                if (count.get() == MAX_TAG_COUNT) {
                    throw new BusinessException(EXCEED_MAX_TAG_COUNT);
                }
                count.addAndGet(1);
                tags.add(new Tag(null, tag));
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
    private Long calculateScore(Long prevScore, List<CreateDiaryRequest.Checklist> checklists) {
        // 점수 계산
        // 이전 score * 0.9 + (부정 체크 * 4 - (부정 체크 안한 갯수 * 1 + 긍정 체크 * 2))

        long score = (long) (prevScore * PREV_SCORE_WEIGHT);

        long badCheckCount = checklists.stream().filter(ele -> ele.getIsChecked() && !ele.getIsGood()).count();
        long goodCheckCount = checklists.stream().filter(ele -> ele.getIsChecked() && ele.getIsGood()).count();

        score += badCheckCount * BAD_CHECK_WEIGHT - ((MAX_CHECKLIST - badCheckCount) * BAD_UNCHECK_WEIGHT + goodCheckCount * GOOD_CHECK_WEIGHT);
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

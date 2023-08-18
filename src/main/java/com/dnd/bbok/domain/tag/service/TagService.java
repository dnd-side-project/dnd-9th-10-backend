package com.dnd.bbok.domain.tag.service;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
import com.dnd.bbok.domain.friend.entity.Friend;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryTagEntity;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendTagEntity;
import com.dnd.bbok.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static com.dnd.bbok.global.exception.ErrorCode.EXCEED_MAX_TAG_COUNT;

/**
 * 태그 생성 및 조회 logic 을 실행하는 서비스
 */
@Service
@RequiredArgsConstructor
public class TagService {
    private static final int MAX_TAG_COUNT = 5;
    private final FriendTagEntityService friendTagEntityService;
    private final DiaryTagEntityService diaryTagEntityService;

    /**
     * 이번 일기 작성에 사용된 tag 들의 id 목록 반환
     * @param friend 친구 엔티티
     * @param tags 이번 일기에 사용한 태그 목록
     */
    @Transactional(rollbackOn = BusinessException.class)
    public List<FriendTagEntity> getFriendTags(Friend friend, List<String> tags) {
        List<FriendTagEntity> targetFriendTagEntities = new ArrayList<>();
        List<FriendTagEntity> friendTagEntities = friendTagEntityService.getFriendTags(friend.getId());
        AtomicInteger tagCount = new AtomicInteger(friendTagEntities.size());
        tags.forEach(tag -> {
            Optional<FriendTagEntity> targetTag = friendTagEntities.stream().filter(ele -> Objects.equals(ele.getFriend().getId(), friend.getId())).findFirst();
            if (targetTag.isPresent()) {
                targetFriendTagEntities.add(targetTag.get());
            } else {
                if (tagCount.get() == MAX_TAG_COUNT) {
                    // 이번에 추가해서 태그 갯수가 5개 이상 된다면 에러
                    throw new BusinessException(EXCEED_MAX_TAG_COUNT);
                }
                tagCount.addAndGet(1);
                // targetFriendTagEntities.add(friendTagEntityService.createTag(tag, friend));
            }
        });
        return targetFriendTagEntities;
    }

    public void createDiaryTag(DiaryEntity diaryEntity, List<FriendTagEntity> friendTagEntities) {
        this.diaryTagEntityService.createDiaryTag(diaryEntity, friendTagEntities);
    }

    public List<DiaryTagEntity> getDiariesTags(List<Long> diaryIds) {
        return this.diaryTagEntityService.getDiariesTags(diaryIds);
    }

    public List<FriendTagEntity> getFriendTagsByFriendId(Long friendId) {
        return this.friendTagEntityService.getFriendTags(friendId);
    }
}

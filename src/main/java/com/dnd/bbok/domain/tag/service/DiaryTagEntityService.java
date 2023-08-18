package com.dnd.bbok.domain.tag.service;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
import com.dnd.bbok.diary.adapter.out.persistence.repository.DiaryTagRepository;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryTagEntity;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendTagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DiaryTag Entity 관련한 DB 작업을 수행하는 서비스
 */
@Service
@RequiredArgsConstructor
public class DiaryTagEntityService {
    private final DiaryTagRepository diaryTagRepository;

    public void createDiaryTag(DiaryEntity diaryEntity, List<FriendTagEntity> friendTagEntities) {
        friendTagEntities.forEach(friendTag ->
            this.diaryTagRepository.save(DiaryTagEntity.builder()
                    .diaryEntity(diaryEntity)
                    .friendTagEntity(friendTag)
                    .build())
        );
    }

    public List<DiaryTagEntity> getDiariesTags(List<Long> diaryIds) {
        return this.diaryTagRepository.findByDiaryIds(diaryIds);
    }
}

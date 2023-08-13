package com.dnd.bbok.domain.tag.service;

import com.dnd.bbok.domain.diary.entity.Diary;
import com.dnd.bbok.domain.tag.repository.DiaryTagRepository;
import com.dnd.bbok.domain.tag.entity.DiaryTag;
import com.dnd.bbok.domain.tag.entity.FriendTag;
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

    public void createDiaryTag(Diary diary, List<FriendTag> friendTags) {
        friendTags.forEach(friendTag ->
            this.diaryTagRepository.save(DiaryTag.builder()
                    .diary(diary)
                    .friendTag(friendTag)
                    .build())
        );
    }

    public List<DiaryTag> getDiariesTags(List<Long> diaryIds) {
        return this.diaryTagRepository.findByDiaryIds(diaryIds);
    }
}

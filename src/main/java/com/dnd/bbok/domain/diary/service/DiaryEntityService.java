package com.dnd.bbok.domain.diary.service;

import com.dnd.bbok.diary.application.port.in.request.CreateDiaryRequest;
import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
import com.dnd.bbok.diary.adapter.out.persistence.repository.DiaryRepository;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Diary Entity 관련한 DB 작업을 수행하는 서비스
 */
@Service
@RequiredArgsConstructor
public class DiaryEntityService {
    private final DiaryRepository diaryRepository;

    public DiaryEntity createDiary(FriendEntity friend, CreateDiaryRequest createDiaryRequest) {
        DiaryEntity diaryEntity = DiaryEntity.builder()
                .friend(friend)
                .contents(createDiaryRequest.getContent())
                .emoji(createDiaryRequest.getEmoji())
                .diaryDate(createDiaryRequest.getDate())
                .build();
        return this.diaryRepository.save(diaryEntity);
    }

    public List<DiaryEntity> getDiariesByFriendId(Long friendId) {
        return this.diaryRepository.findAllByFriendId(friendId);
    }

    public int countDiariesByFriendId(Long friendId) {
        return this.diaryRepository.countDiaries(friendId);
    }
}

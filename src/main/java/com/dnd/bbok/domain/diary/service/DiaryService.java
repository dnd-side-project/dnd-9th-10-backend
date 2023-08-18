package com.dnd.bbok.domain.diary.service;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 일기 생성 및 조회 Logic 을 구현한 Service
 */
@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryEntityService diaryEntityService;

    // TODO Paging 기능 구현
    public List<DiaryEntity> getDiariesByFriendId(Long friendId) {
        return this.diaryEntityService.getDiariesByFriendId(friendId);
    }
}

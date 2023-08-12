package com.dnd.bbok.domain.diary.service;

import com.dnd.bbok.domain.diary.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Diary Entity 관련한 DB 작업을 수행하는 서비스
 */
@Service
@RequiredArgsConstructor
public class DiaryEntityService {
    private final FriendRepository friendRepository;
}

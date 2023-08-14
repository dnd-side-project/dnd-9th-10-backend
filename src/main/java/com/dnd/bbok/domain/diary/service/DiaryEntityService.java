package com.dnd.bbok.domain.diary.service;

import com.dnd.bbok.domain.diary.dto.request.DiaryRequestDto;
import com.dnd.bbok.domain.diary.entity.Diary;
import com.dnd.bbok.domain.diary.repository.DiaryRepository;
import com.dnd.bbok.domain.friend.entity.Friend;
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

    public Diary createDiary(Friend friend, DiaryRequestDto diaryRequestDto) {
        Diary diary = Diary.builder()
                .friend(friend)
                .contents(diaryRequestDto.getContent())
                .emoji(diaryRequestDto.getEmoji())
                .diaryDate(diaryRequestDto.getDate())
                .build();
        return this.diaryRepository.save(diary);
    }

    public List<Diary> getDiariesByFriendId(Long friendId) {
        return this.diaryRepository.findAllByFriendId(friendId);
    }

    public int countDiariesByFriendId(Long friendId) {
        return this.diaryRepository.countDiaries(friendId);
    }
}

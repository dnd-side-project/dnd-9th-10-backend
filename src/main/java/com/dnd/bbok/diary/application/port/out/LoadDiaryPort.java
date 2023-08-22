package com.dnd.bbok.diary.application.port.out;

import com.dnd.bbok.diary.domain.Diary;
import org.springframework.data.domain.Page;

public interface LoadDiaryPort {
    Diary loadDiary(Long diaryId);
    Page<Diary> loadDiaries(Long friendId, Integer offset, String order, String keyword, String tag);
    int countDiariesByFriendId(Long friendId);
}

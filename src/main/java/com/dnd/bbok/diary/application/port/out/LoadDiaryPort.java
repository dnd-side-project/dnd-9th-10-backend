package com.dnd.bbok.diary.application.port.out;

import com.dnd.bbok.diary.domain.Diary;

import java.util.List;

public interface LoadDiaryPort {
    Diary loadDiary(Long diaryId);
    List<Diary> loadDiaries(Long friendId);
    int countDiariesByFriendId(Long friendId);
}

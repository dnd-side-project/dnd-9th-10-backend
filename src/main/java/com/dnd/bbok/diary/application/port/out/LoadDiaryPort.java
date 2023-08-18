package com.dnd.bbok.diary.application.port.out;

import com.dnd.bbok.diary.domain.Diary;

public interface LoadDiaryPort {
    Diary loadDiary(Long diaryId);
}

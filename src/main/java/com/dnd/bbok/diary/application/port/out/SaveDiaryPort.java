package com.dnd.bbok.diary.application.port.out;

import com.dnd.bbok.diary.domain.Diary;

public interface SaveDiaryPort {
    void createDiary(Long friendId, Diary diary);
}

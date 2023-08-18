package com.dnd.bbok.diary.application.port.out;

import com.dnd.bbok.diary.domain.Diary;

public interface SaveDiaryPort {
    void saveDiary(Long friendId, Diary diary);
}

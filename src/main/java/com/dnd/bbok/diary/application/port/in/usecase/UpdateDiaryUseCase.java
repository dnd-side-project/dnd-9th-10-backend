package com.dnd.bbok.diary.application.port.in.usecase;

import com.dnd.bbok.diary.application.port.in.request.UpdateDiaryRequest;

import java.util.UUID;

public interface UpdateDiaryUseCase {
    void updateDiary(UUID memberId, Long diaryId, UpdateDiaryRequest updateDiaryRequest);
}

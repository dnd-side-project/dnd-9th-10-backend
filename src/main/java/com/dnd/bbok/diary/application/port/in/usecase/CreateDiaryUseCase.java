package com.dnd.bbok.diary.application.port.in.usecase;

import com.dnd.bbok.diary.application.port.in.request.CreateDiaryRequest;
import com.dnd.bbok.diary.application.port.in.response.CreateDiaryResponse;

import java.util.UUID;

public interface CreateDiaryUseCase {
    CreateDiaryResponse createDiary(UUID memberId, Long friendId, CreateDiaryRequest createDiaryRequest);
}

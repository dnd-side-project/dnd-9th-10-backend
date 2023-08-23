package com.dnd.bbok.diary.application.service;

import com.dnd.bbok.diary.application.port.in.usecase.DeleteDiaryUseCase;
import com.dnd.bbok.diary.application.port.out.LoadDiaryPort;
import com.dnd.bbok.diary.application.port.out.SaveDiaryPort;
import com.dnd.bbok.diary.domain.Diary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteDiaryService implements DeleteDiaryUseCase {
    private final LoadDiaryPort loadDiaryPort;
    private final SaveDiaryPort saveDiaryPort;

    @Override
    public void deleteDiary(Long diaryId) {
        Diary diary = loadDiaryPort.loadDiary(diaryId);
        diary.setIsDeleted(true);
        saveDiaryPort.saveDiary(diary);

    }
}

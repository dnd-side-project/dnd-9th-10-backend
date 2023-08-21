package com.dnd.bbok.diary.application.port.in.usecase;

import com.dnd.bbok.diary.application.port.in.response.GetDiaryResponse;

public interface GetDiaryQuery {
    GetDiaryResponse getDiary(Long diaryId);
}

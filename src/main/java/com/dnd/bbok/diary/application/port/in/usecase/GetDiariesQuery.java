package com.dnd.bbok.diary.application.port.in.usecase;

import com.dnd.bbok.diary.application.port.in.response.GetDiariesResponse;

public interface GetDiariesQuery {
    GetDiariesResponse getDiariesQuery(Long friendId, Integer offset, String order, String keyword, String tag);
}

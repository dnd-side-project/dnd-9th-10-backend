package com.dnd.bbok.diary.application.service;

import com.dnd.bbok.diary.application.port.in.response.GetDiariesResponse;
import com.dnd.bbok.diary.application.port.in.response.GetDiaryResponse;
import com.dnd.bbok.diary.application.port.in.usecase.GetDiariesQuery;
import com.dnd.bbok.diary.application.port.in.usecase.GetDiaryQuery;
import com.dnd.bbok.diary.application.port.out.LoadDiaryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetDiaryService implements GetDiariesQuery, GetDiaryQuery {
    private final LoadDiaryPort loadDiaryPort;

    @Override
    public GetDiariesResponse getDiariesQuery(Long friendId, Integer offset, String order, String keyword, String tag) {
        List<GetDiaryResponse> getDiaryResponses = loadDiaryPort.loadDiaries(friendId).stream().map(GetDiaryResponse::new).collect(Collectors.toList());
        return new GetDiariesResponse(getDiaryResponses);
    }

    @Override
    public GetDiaryResponse getDiary(Long diaryId) {
        return new GetDiaryResponse(loadDiaryPort.loadDiary(diaryId));
    }
}

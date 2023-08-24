package com.dnd.bbok.diary.application.service;

import com.dnd.bbok.diary.application.port.in.response.GetDiariesResponse;
import com.dnd.bbok.diary.application.port.in.response.GetDiaryResponse;
import com.dnd.bbok.diary.application.port.in.usecase.GetDiariesQuery;
import com.dnd.bbok.diary.application.port.in.usecase.GetDiaryQuery;
import com.dnd.bbok.diary.application.port.out.LoadDiaryPort;
import com.dnd.bbok.diary.domain.Diary;
import com.dnd.bbok.infra.s3.service.S3Downloader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDiaryService implements GetDiariesQuery, GetDiaryQuery {
    private final LoadDiaryPort loadDiaryPort;
    private final S3Downloader s3Downloader;
    @Override
    public GetDiariesResponse getDiariesQuery(Long friendId, Integer offset, String order, String keyword, String tag) {
        Page<Diary> diaries = loadDiaryPort.loadDiaries(friendId, offset,order, keyword, tag);
        diaries.forEach(diary -> {
            if (diary.getEmoji() != null) {
                diary.setEmojiUrl(s3Downloader.getIconUrl(diary.getEmoji().getIconFile()));
            }
        });
        return new GetDiariesResponse(diaries);
    }

    @Override
    public GetDiaryResponse getDiary(Long diaryId) {
        Diary diary = loadDiaryPort.loadDiary(diaryId);
        diary.setEmojiUrl(s3Downloader.getIconUrl(diary.getEmoji().getIconFile()));
        return new GetDiaryResponse(diary);
    }
}

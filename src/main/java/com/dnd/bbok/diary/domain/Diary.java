package com.dnd.bbok.diary.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class Diary {
    private final Long id;
    private final Emoji emoji;
    private final String contents;
    private final LocalDate diaryDate;
    private final String sticker;
    private final List<Tag> tags;
    private final List<DiaryChecklist> diaryChecklist;

    public Diary(Long id, Emoji emoji, String contents, LocalDate diaryDate, String sticker, List<Tag> tags, List<DiaryChecklist> diaryChecklist) {
        this.id = id;
        this.emoji = emoji;
        this.contents = contents;
        this.diaryDate = diaryDate;
        this.sticker = sticker;
        this.tags = tags;
        this.diaryChecklist = diaryChecklist;
    }
}

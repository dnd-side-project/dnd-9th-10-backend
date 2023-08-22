package com.dnd.bbok.diary.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Diary {
    private Long id;
    private Long friendId;
    private Emoji emoji;
    private String contents;
    private LocalDate diaryDate;
    private String sticker;
    private Integer diaryScore;
    private Boolean isDeleted;
    private List<Tag> tags;
    private List<DiaryChecklist> diaryChecklist;

    public Diary(Long id, Long friendId, Emoji emoji, String contents, LocalDate diaryDate, String sticker, Integer diaryScore, Boolean isDeleted, List<Tag> tags, List<DiaryChecklist> diaryChecklist) {
        this.id = id;
        this.friendId = friendId;
        this.emoji = emoji;
        this.contents = contents;
        this.diaryDate = diaryDate;
        this.sticker = sticker;
        this.diaryScore = diaryScore;
        this.isDeleted = isDeleted;
        this.tags = Objects.requireNonNullElseGet(tags, ArrayList::new);
        this.diaryChecklist = Objects.requireNonNullElseGet(diaryChecklist, ArrayList::new);
    }
}

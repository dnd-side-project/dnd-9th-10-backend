package com.dnd.bbok.diary.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Diary {
    private final Long  id;
    private final Long friendId;
    private Emoji emoji;
    private String emojiUrl = null;
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

    public void setEmoji(Emoji emoji) {
        this.emoji = emoji;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setDiaryDate(LocalDate diaryDate) {
        this.diaryDate = diaryDate;
    }

    public void setSticker(String sticker) {
        this.sticker = sticker;
    }

    public void setDiaryScore(Integer diaryScore) {
        this.diaryScore = diaryScore;
    }

    public void setIsDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setDiaryChecklist(List<DiaryChecklist> diaryChecklist) {
        this.diaryChecklist = diaryChecklist;
    }

    public void setEmojiUrl(String emojiUrl) {
        this.emojiUrl = emojiUrl;
    }
}

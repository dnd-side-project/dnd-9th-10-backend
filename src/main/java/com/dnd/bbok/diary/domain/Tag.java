package com.dnd.bbok.diary.domain;

import lombok.Getter;

@Getter
public class Tag {
    private Long id;
    private Long friendTagId;

    private final String tag;

    private Long diaryId = null;

    public Tag(String tag) {
        this.tag = tag;
    }

    public Tag(Long friendTagId, String tag) {
        this.friendTagId = friendTagId;
        this.tag = tag;
    }

    public Tag(Long id, Long friendTagId, String tag, Long diaryId) {
        this.id = id;
        this.friendTagId = friendTagId;
        this.tag = tag;
        this.diaryId = diaryId;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

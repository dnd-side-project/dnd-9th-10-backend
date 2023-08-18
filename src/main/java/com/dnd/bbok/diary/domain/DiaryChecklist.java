package com.dnd.bbok.diary.domain;

import lombok.Getter;


@Getter
public class DiaryChecklist {
    private final Long id;
    private final Boolean isChecked;
    private final Long memberChecklistId;

    public DiaryChecklist(Long id, Boolean isChecked, Long memberChecklistId) {
        this.id = id;
        this.isChecked = isChecked;
        this.memberChecklistId = memberChecklistId;
    }
}

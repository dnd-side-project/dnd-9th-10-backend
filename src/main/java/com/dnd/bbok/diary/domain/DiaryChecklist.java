package com.dnd.bbok.diary.domain;

import lombok.Getter;


@Getter
public class DiaryChecklist {
    private final Long id;
    private final Boolean isChecked;
    private final Long memberChecklistId;
    private final String criteria;
    private final Long diaryId;
    private final Boolean isGood;

    public DiaryChecklist(Long id, Boolean isChecked, Long memberChecklistId) {
        this.id = id;
        this.isChecked = isChecked;
        this.memberChecklistId = memberChecklistId;
        this.criteria = null;
        this.diaryId = null;
        this.isGood = null;
    }

    public DiaryChecklist(Long id, Long memberChecklistId, Boolean isChecked, String criteria, Long diaryId, Boolean isGood) {
        this.id = id;
        this.isChecked = isChecked;
        this.memberChecklistId = memberChecklistId;
        this.criteria = criteria;
        this.diaryId = diaryId;
        this.isGood = isGood;
    }
}

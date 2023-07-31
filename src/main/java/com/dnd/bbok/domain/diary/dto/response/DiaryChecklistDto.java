package com.dnd.bbok.domain.diary.dto.response;

import lombok.Getter;

@Getter
public class DiaryChecklistDto {
    private final String criteria;
    private final boolean isChecked;

    public DiaryChecklistDto(String criteria, boolean isChecked) {
        this.criteria = criteria;
        this.isChecked = isChecked;
    }
}

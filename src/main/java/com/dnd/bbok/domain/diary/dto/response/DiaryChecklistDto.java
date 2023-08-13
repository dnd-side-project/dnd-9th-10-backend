package com.dnd.bbok.domain.diary.dto.response;

import lombok.Getter;


@Getter
public class DiaryChecklistDto {
    private final Long id;
    private final String criteria;
    private final Boolean isChecked;

    public DiaryChecklistDto(Long id, String criteria, boolean isChecked) {
        this.id = id;
        this.criteria = criteria;
        this.isChecked = isChecked;
    }
}

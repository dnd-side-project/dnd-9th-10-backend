package com.dnd.bbok.domain.diary.dto.response;

import lombok.Getter;

import java.util.Random;

@Getter
public class DiaryChecklistDto {
    private final Long id;
    private final String criteria;
    private final Boolean isChecked;

    public DiaryChecklistDto(String criteria, boolean isChecked) {
        this.id = new Random().nextLong(21);
        this.criteria = criteria;
        this.isChecked = isChecked;
    }
}

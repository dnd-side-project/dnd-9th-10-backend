package com.dnd.bbok.domain.diary.dto.response;

import lombok.Getter;

import java.util.Random;

@Getter
public class DiaryChecklistDto {
    private final int id;
    private final String criteria;
    private final boolean isChecked;

    public DiaryChecklistDto(String criteria, boolean isChecked) {
        this.id = new Random().nextInt(21);
        this.criteria = criteria;
        this.isChecked = isChecked;
    }
}

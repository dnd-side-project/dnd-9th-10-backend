package com.dnd.bbok.domain.diary.dto.request;

import lombok.Getter;

@Getter
public class ChecklistDto {
    private Long id;
    private Boolean isGood;
    private Boolean isChecked;
}

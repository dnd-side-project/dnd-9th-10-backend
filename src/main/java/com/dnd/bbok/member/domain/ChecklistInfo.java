package com.dnd.bbok.member.domain;

import lombok.Getter;

@Getter
public class ChecklistInfo {
    Long id;
    String criteria;

    public ChecklistInfo(Long id, String criteria) {
        this.id = id;
        this.criteria = criteria;
    }
}

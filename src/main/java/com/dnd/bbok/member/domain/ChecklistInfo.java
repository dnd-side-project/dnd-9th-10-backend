package com.dnd.bbok.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ChecklistInfo {
    private Long id;
    private String criteria;
    private boolean isGood;
    private boolean isUsed;

//    public ChecklistInfo(Long id, String criteria) {
//        this.id = id;
//        this.criteria = criteria;
//    }

    @Builder
    public ChecklistInfo(Long id, String criteria, boolean isGood, boolean isUsed) {
        this.id = id;
        this.criteria = criteria;
        this.isGood = isGood;
        this.isUsed = isUsed;
    }

    public void setUsingStatus(boolean status) {
        this.isUsed = status;
    }
}

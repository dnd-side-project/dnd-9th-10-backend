package com.dnd.bbok.member.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class MemberChecklist {
    List<ChecklistInfo> goodChecklist;
    List<ChecklistInfo> badChecklist;

    public MemberChecklist(List<ChecklistInfo> goodChecklist, List<ChecklistInfo> badChecklist) {
        this.goodChecklist = goodChecklist;
        this.badChecklist = badChecklist;
    }
}

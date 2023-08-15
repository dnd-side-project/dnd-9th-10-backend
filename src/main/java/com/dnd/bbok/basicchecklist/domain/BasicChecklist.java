package com.dnd.bbok.basicchecklist.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BasicChecklist {
    private final List<String> goodChecklist;
    private final List<String> badChecklist;

    @Builder
    public BasicChecklist(List<String> goodChecklist, List<String> badChecklist) {
        this.goodChecklist = goodChecklist;
        this.badChecklist = badChecklist;
    }
}

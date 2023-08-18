package com.dnd.bbok.saying.domain;

import lombok.Getter;

@Getter
public class Saying {
    private final Long id;
    private final String contents;
    private final String reference;

    public Saying(Long id, String contents, String reference) {
        this.id = id;
        this.contents = contents;
        this.reference = reference;
    }
}

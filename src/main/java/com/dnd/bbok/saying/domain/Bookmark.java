package com.dnd.bbok.saying.domain;

import com.dnd.bbok.member.domain.Member;
import lombok.Getter;

@Getter
public class Bookmark {
    private final Long id;
    private final Member member;
    private final Saying saying;

    public Bookmark(Long id, Member member, Saying saying) {
        this.id = id;
        this.member = member;
        this.saying = saying;
    }
}

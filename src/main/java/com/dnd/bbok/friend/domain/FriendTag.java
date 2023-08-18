package com.dnd.bbok.friend.domain;

import lombok.Getter;

@Getter
public class FriendTag {
    private final Long id;
    private final String name;

    public FriendTag(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

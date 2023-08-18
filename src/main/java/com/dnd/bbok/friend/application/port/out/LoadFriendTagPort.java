package com.dnd.bbok.friend.application.port.out;

import com.dnd.bbok.friend.domain.FriendTag;

import java.util.List;

public interface LoadFriendTagPort {
    List<FriendTag> loadFriendTag(Long friendId);
}

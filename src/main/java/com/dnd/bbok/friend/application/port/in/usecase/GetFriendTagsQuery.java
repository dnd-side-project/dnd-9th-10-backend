package com.dnd.bbok.friend.application.port.in.usecase;

import com.dnd.bbok.friend.application.port.in.response.GetFriendTagsResponse;

public interface GetFriendTagsQuery {
    GetFriendTagsResponse getTags(Long friendId);
}

package com.dnd.bbok.friend.application.service;

import com.dnd.bbok.friend.application.port.in.response.GetFriendTagsResponse;
import com.dnd.bbok.friend.application.port.in.usecase.GetFriendTagsQuery;
import com.dnd.bbok.friend.application.port.out.LoadFriendTagPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetFriendTagsService implements GetFriendTagsQuery {

    private final LoadFriendTagPort loadFriendTagPort;
    @Override
    public GetFriendTagsResponse getTags(Long friendId) {

        return new GetFriendTagsResponse(loadFriendTagPort.loadFriendTag(friendId));
    }
}

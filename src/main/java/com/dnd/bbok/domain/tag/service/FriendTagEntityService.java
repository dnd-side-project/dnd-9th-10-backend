package com.dnd.bbok.domain.tag.service;

import com.dnd.bbok.domain.tag.repository.FriendTagRepository;
import com.dnd.bbok.domain.friend.entity.Friend;
import com.dnd.bbok.domain.tag.entity.FriendTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FriendTag Entity 관련한 DB 작업을 수행하는 서비스
 */
@Service
@RequiredArgsConstructor
public class FriendTagEntityService {
    private final FriendTagRepository friendTagRepository;

    public List<FriendTag> getFriendTags(long friendId) {
        return this.friendTagRepository.findAllByFriendId(friendId);
    }

    public FriendTag createTag(String name, Friend friend) {
        return this.friendTagRepository.save(FriendTag.builder()
                .name(name)
                .friend(friend)
                .build());
    }
}

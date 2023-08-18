package com.dnd.bbok.domain.tag.service;

import com.dnd.bbok.friend.adapter.out.persistence.repository.FriendTagRepository;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendTagEntity;
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

    public List<FriendTagEntity> getFriendTags(long friendId) {
        return this.friendTagRepository.findAllByFriendId(friendId);
    }
}

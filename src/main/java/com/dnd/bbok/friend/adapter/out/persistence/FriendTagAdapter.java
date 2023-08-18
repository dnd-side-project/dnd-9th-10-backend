package com.dnd.bbok.friend.adapter.out.persistence;

import com.dnd.bbok.friend.adapter.out.persistence.mapper.FriendTagMapper;
import com.dnd.bbok.friend.adapter.out.persistence.repository.FriendTagRepository;
import com.dnd.bbok.friend.application.port.out.LoadFriendTagPort;
import com.dnd.bbok.friend.domain.FriendTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FriendTagAdapter implements LoadFriendTagPort {
    private final FriendTagRepository friendTagRepository;
    private final FriendTagMapper friendTagMapper;

    @Override
    public List<FriendTag> loadFriendTag(Long friendId) {
        return friendTagMapper.toEntity(friendTagRepository.findAllByFriendId(friendId));
    }
}

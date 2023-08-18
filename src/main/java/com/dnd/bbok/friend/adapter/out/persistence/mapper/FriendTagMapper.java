package com.dnd.bbok.friend.adapter.out.persistence.mapper;

import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendTagEntity;
import com.dnd.bbok.friend.domain.FriendTag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FriendTagMapper {
    public List<FriendTag> toEntity(List<FriendTagEntity> friendTagEntities) {
        return friendTagEntities.stream().map(ele -> new FriendTag(ele.getId(), ele.getName())).collect(Collectors.toList());
    }
}

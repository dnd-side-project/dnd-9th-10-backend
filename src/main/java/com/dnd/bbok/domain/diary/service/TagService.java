package com.dnd.bbok.domain.diary.service;

import com.dnd.bbok.domain.tag.entity.FriendTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TagService {
    private final FriendTagEntityService friendTagEntityService;

    public void checkAndCreateFriendTags(Long friendId, List<String> tags) {
        List<FriendTag> friendTags = friendTagEntityService.getFriendTags(friendId);
        tags.forEach(tag -> {

            if (friendTags.stream().noneMatch(ele -> Objects.equals(ele.getFriend().getId(), friendId))) {

            };
        });
    }
}

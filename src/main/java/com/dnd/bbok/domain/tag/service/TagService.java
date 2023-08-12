package com.dnd.bbok.domain.tag.service;

import com.dnd.bbok.domain.friend.entity.Friend;
import com.dnd.bbok.domain.tag.entity.FriendTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 태그 생성 및 조회 logic 을 실행하는 서비스
 */
@Service
@RequiredArgsConstructor
public class TagService {
    private final FriendTagEntityService friendTagEntityService;


    /**
     * 이번 일기 작성에 사용된 tag 들의 id 목록 반환
     * @param friend 친구 엔티티
     * @param tags 이번 일기에 사용한 태그 목록
     */
    public List<FriendTag> getFriendTags(Friend friend, List<String> tags) {
        List<FriendTag> targetFriendTags = new ArrayList<>();
        List<FriendTag> friendTags = friendTagEntityService.getFriendTags(friend.getId());
        tags.forEach(tag -> {
            Optional<FriendTag> targetTag = friendTags.stream().filter(ele -> Objects.equals(ele.getFriend().getId(), friend.getId())).findFirst();
            if (targetTag.isPresent()) {
                targetFriendTags.add(targetTag.get());
            } else {
                targetFriendTags.add(friendTagEntityService.createTag(tag, friend));
            }
        });
        return targetFriendTags;
    }
}

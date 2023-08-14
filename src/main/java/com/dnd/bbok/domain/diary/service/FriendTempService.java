package com.dnd.bbok.domain.diary.service;

import com.dnd.bbok.domain.friend.entity.Friend;
import com.dnd.bbok.domain.friend.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Friend Entity 에 접근하기 위한 임시 서비스 (TODO Friend Merge 후 삭제)
 */
@Service
@RequiredArgsConstructor
public class FriendTempService {
    private final FriendRepository friendRepository;

    public Friend getFriend(Long id) {
        return friendRepository.findById(id).orElseThrow();
    }

    public Friend updateFriendScore(Friend friend, Long score) {
        friend.changeFriendScore(score);
        return this.friendRepository.save(friend);
    }
}

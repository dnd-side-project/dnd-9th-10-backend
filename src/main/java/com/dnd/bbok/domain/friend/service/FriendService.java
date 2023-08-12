package com.dnd.bbok.domain.friend.service;

import static com.dnd.bbok.global.exception.ErrorCode.OTHER_FRIEND_ALREADY_ACTIVE;

import com.dnd.bbok.domain.friend.entity.Friend;
import com.dnd.bbok.domain.friend.repository.FriendRepository;
import com.dnd.bbok.domain.member.entity.Member;
import com.dnd.bbok.global.exception.BusinessException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FriendService {

  private final FriendRepository friendRepository;

  @Transactional
  public void saveFriend(Friend friend) {
    friendRepository.save(friend);
  }


  @Transactional(readOnly = true)
  public void checkOtherActiveFriend(Member member) {
    List<Friend> otherFriends = friendRepository.findOtherFriend(member.getId());
    for(Friend each : otherFriends) {
      if(each.isActive()) {
        throw new BusinessException(OTHER_FRIEND_ALREADY_ACTIVE);
      }
    }
  }
}

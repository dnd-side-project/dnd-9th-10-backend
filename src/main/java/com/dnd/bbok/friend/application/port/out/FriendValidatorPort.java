package com.dnd.bbok.friend.application.port.out;


import com.dnd.bbok.friend.domain.Friend;
import com.dnd.bbok.member.domain.Member;
import java.util.UUID;

public interface FriendValidatorPort {

  void checkOtherActiveFriend(Member member);

  void validateNaming(String friendName);

  Friend isActiveFriend(UUID memberId, Long friendId);
}

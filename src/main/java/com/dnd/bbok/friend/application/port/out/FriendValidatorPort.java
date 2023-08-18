package com.dnd.bbok.friend.application.port.out;


import com.dnd.bbok.member.domain.Member;

public interface FriendValidatorPort {

  void checkOtherActiveFriend(Member member);

  void validateNaming(String friendName);
}

package com.dnd.bbok.friend.application.port.out;

import com.dnd.bbok.friend.domain.Friend;
import com.dnd.bbok.member.domain.Member;

public interface SaveFriendPort {

  void saveFriend(Member member, Friend friend);

}

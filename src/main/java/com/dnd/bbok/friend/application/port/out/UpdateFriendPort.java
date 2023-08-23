package com.dnd.bbok.friend.application.port.out;

import com.dnd.bbok.friend.domain.Friend;

public interface UpdateFriendPort {

  //TODO: 승훈님께 여쭤보고 반영하기
  void updateFriendScore(Friend friend, Long score);

}

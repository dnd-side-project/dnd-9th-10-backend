package com.dnd.bbok.friend.application.port.out;

import com.dnd.bbok.friend.application.port.in.request.UpdateFriendRequest;
import com.dnd.bbok.friend.domain.Friend;
import java.util.UUID;

public interface UpdateFriendPort {

  void updateFriend(Friend friend, UpdateFriendRequest friendRequest);

  void updateStatus(UUID memberId, Friend friend);

}

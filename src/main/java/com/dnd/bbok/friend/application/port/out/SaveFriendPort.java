package com.dnd.bbok.friend.application.port.out;

import com.dnd.bbok.friend.application.port.in.request.FriendRequestDto;
import java.util.UUID;

public interface SaveFriendPort {

  void saveFriend(UUID memberId, FriendRequestDto friendRequest);

}

package com.dnd.bbok.friend.application.port.out;

import com.dnd.bbok.friend.application.port.in.request.CreateFriendRequest;
import java.util.UUID;

public interface SaveFriendPort {

  void saveFriend(UUID memberId, CreateFriendRequest friendRequest);

}

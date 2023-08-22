package com.dnd.bbok.friend.application.port.in.usecase;

import com.dnd.bbok.friend.application.port.in.response.GetFriendGroupResponse;
import java.util.UUID;

public interface GetFriendsQuery {

  GetFriendGroupResponse getFriends(UUID memberID);

}

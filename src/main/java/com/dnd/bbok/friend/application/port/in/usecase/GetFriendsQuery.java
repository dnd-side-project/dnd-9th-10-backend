package com.dnd.bbok.friend.application.port.in.usecase;

import com.dnd.bbok.friend.application.port.in.response.FriendGroupInfo;
import java.util.UUID;

public interface GetFriendsQuery {

  FriendGroupInfo getFriends(UUID memberID);

}

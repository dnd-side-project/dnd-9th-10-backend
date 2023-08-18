package com.dnd.bbok.friend.application.port.in.usecase;

import com.dnd.bbok.friend.application.port.in.response.FriendsDto;
import java.util.UUID;

public interface GetFriendsQuery {

  FriendsDto getFriends(UUID memberID);

}

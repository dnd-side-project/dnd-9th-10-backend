package com.dnd.bbok.friend.application.port.out;

import com.dnd.bbok.friend.domain.Friend;
import java.util.List;
import java.util.UUID;

public interface LoadFriendPort {

  List<Friend> getByMemberId(UUID memberId);

}

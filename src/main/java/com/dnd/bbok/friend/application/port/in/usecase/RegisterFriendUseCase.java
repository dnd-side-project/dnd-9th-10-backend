package com.dnd.bbok.friend.application.port.in.usecase;

import com.dnd.bbok.friend.application.port.in.request.FriendInfoRequest;
import java.util.UUID;

public interface RegisterFriendUseCase {

  void createFriendCharacter(UUID uuid, FriendInfoRequest friend);

}

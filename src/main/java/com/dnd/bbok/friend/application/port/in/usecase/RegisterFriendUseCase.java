package com.dnd.bbok.friend.application.port.in.usecase;

import com.dnd.bbok.friend.application.port.in.request.FriendRequestDto;
import java.util.UUID;

public interface RegisterFriendUseCase {

  void createFriendCharacter(UUID uuid, FriendRequestDto friend);

}

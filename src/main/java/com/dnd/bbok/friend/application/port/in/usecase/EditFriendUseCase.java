package com.dnd.bbok.friend.application.port.in.usecase;

import com.dnd.bbok.friend.application.port.in.request.UpdateFriendRequest;
import java.util.UUID;

public interface EditFriendUseCase {

  void editName(UUID memberId, Long friendId, UpdateFriendRequest updateFriendRequest);

  void editStatus(UUID memberId, Long friendId);
}

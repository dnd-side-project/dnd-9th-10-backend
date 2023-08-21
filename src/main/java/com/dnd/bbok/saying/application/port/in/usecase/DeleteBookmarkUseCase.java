package com.dnd.bbok.saying.application.port.in.usecase;

import java.util.UUID;

public interface DeleteBookmarkUseCase {

  void deleteBookmark(UUID memberId, Long sayingId);

}

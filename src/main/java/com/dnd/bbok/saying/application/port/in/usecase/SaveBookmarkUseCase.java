package com.dnd.bbok.saying.application.port.in.usecase;

import com.dnd.bbok.saying.application.port.in.request.BookmarkRequestDto;
import java.util.UUID;

public interface SaveBookmarkUseCase {
  void bookmarkSaying(UUID memberId, BookmarkRequestDto bookmarkRequest);

}

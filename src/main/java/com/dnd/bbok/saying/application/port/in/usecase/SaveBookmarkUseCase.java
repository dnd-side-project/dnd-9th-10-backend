package com.dnd.bbok.saying.application.port.in.usecase;

import com.dnd.bbok.saying.application.port.in.request.CreateBookmarkRequest;
import java.util.UUID;

public interface SaveBookmarkUseCase {

  void createBookmark(UUID memberId, CreateBookmarkRequest bookmarkRequest);

}

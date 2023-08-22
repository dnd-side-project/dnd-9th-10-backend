package com.dnd.bbok.saying.application.port.in.usecase;

import com.dnd.bbok.saying.application.port.in.response.GetBookmarkGroupResponse;
import java.util.UUID;

public interface GetBookmarksQuery {

  GetBookmarkGroupResponse getBookmarks(UUID memberId);

}

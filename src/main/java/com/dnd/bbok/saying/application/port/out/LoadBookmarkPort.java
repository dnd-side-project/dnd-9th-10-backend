package com.dnd.bbok.saying.application.port.out;

import com.dnd.bbok.saying.domain.Bookmark;
import java.util.List;
import java.util.UUID;

public interface LoadBookmarkPort {
    boolean isMarked(UUID memberId, Long sayingId);

    List<Bookmark> getBookmarks(UUID memberId);
}

package com.dnd.bbok.saying.application.port.out;

import com.dnd.bbok.saying.domain.Bookmark;
import com.dnd.bbok.saying.domain.Saying;

import java.util.List;

public interface LoadSayingPort {
    List<Saying> getAllSaying();

    Saying getSaying(Long sayingId);

    List<Saying> getBookmarkSaying(List<Bookmark> bookmarks);
}

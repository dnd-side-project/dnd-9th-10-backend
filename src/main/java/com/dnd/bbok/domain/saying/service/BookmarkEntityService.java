package com.dnd.bbok.domain.saying.service;

import com.dnd.bbok.domain.saying.entity.Bookmark;
import com.dnd.bbok.domain.saying.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Diary Entity 관련한 DB 작업을 수행하는 서비스
 */
@Service
@RequiredArgsConstructor
public class BookmarkEntityService {
    private final BookmarkRepository bookmarkRepository;

    public boolean isMarked(UUID memberId, Long sayingId) {
        Optional<Bookmark> bookmark = this.bookmarkRepository.findByMemberIdAndSayingId(memberId, sayingId);
        return bookmark.isPresent();
    }
}

package com.dnd.bbok.domain.saying.service;

import com.dnd.bbok.saying.adapter.out.persistence.entity.BookmarkEntity;
import com.dnd.bbok.saying.adapter.out.persistence.repository.BookmarkRepository;
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

    //TODO: 바꾸기
    public boolean isMarked(UUID memberId, Long sayingId) {
        Optional<BookmarkEntity> bookmark = this.bookmarkRepository.findBookmark(sayingId, memberId);
        return bookmark.isPresent();
    }
}

package com.dnd.bbok.saying.adapter.out.persistence;

import com.dnd.bbok.saying.adapter.out.persistence.entity.BookmarkEntity;
import com.dnd.bbok.saying.adapter.out.persistence.repository.BookmarkRepository;
import com.dnd.bbok.saying.application.port.out.LoadBookmarkPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BookmarkAdapter implements LoadBookmarkPort {
    private final BookmarkRepository bookmarkRepository;
    @Override
    public boolean isMarked(UUID memberId, Long sayingId) {
        Optional<BookmarkEntity> bookmarkEntity = bookmarkRepository.findByMemberIdAndSayingEntityId(memberId, sayingId);
        return bookmarkEntity.isPresent();
    }
}

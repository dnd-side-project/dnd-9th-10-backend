package com.dnd.bbok.saying.adapter.out.persistence;

import static com.dnd.bbok.global.exception.ErrorCode.*;

import com.dnd.bbok.global.exception.BusinessException;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
import com.dnd.bbok.member.adapter.out.persistence.mapper.MemberMapper;

import com.dnd.bbok.saying.adapter.out.persistence.entity.BookmarkEntity;
import com.dnd.bbok.saying.adapter.out.persistence.entity.SayingEntity;
import com.dnd.bbok.saying.adapter.out.persistence.mapper.BookmarkMapper;
import com.dnd.bbok.saying.adapter.out.persistence.mapper.SayingMapper;
import com.dnd.bbok.saying.adapter.out.persistence.repository.BookmarkRepository;
import com.dnd.bbok.saying.application.port.out.BookmarkSayingPort;
import com.dnd.bbok.saying.application.port.out.LoadBookmarkPort;
import com.dnd.bbok.saying.domain.Bookmark;
import com.dnd.bbok.saying.domain.Saying;
import com.dnd.bbok.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BookmarkAdapter implements LoadBookmarkPort, BookmarkSayingPort {
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;
    private final MemberMapper memberMapper;
    private final SayingMapper sayingMapper;

    //TODO: 바꾸기
    @Override
    public boolean isMarked(UUID memberId, Long sayingId) {
        Optional<BookmarkEntity> bookmarkEntity = bookmarkRepository.findBookmark(sayingId, memberId);
        return bookmarkEntity.isPresent();
    }

    @Override
    public Bookmark checkBookmark(Saying saying, Member member) {
        Optional<BookmarkEntity> bookmark =
            bookmarkRepository.findBookmark(saying.getId(), member.getId());
        if(bookmark.isPresent()) {
            throw new BusinessException(ALREADY_BOOKMARKED);
        }
        return bookmarkMapper.toDomain(member, saying);
    }

    @Transactional
    @Override
    public void saveBookmark(Bookmark bookmark) {
        MemberEntity memberEntity = memberMapper.toEntityWithId(bookmark.getMember());
        SayingEntity sayingEntity = sayingMapper.toEntity(bookmark.getSaying());
        BookmarkEntity bookmarkEntity = bookmarkMapper.toEntity(memberEntity,
            sayingEntity);
        bookmarkRepository.save(bookmarkEntity);
    }
}

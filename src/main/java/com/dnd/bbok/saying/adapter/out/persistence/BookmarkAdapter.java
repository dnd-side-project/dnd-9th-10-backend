package com.dnd.bbok.saying.adapter.out.persistence;

import static com.dnd.bbok.global.exception.ErrorCode.*;

import com.dnd.bbok.global.exception.BusinessException;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
import com.dnd.bbok.member.adapter.out.persistence.mapper.MemberMapper;
import com.dnd.bbok.member.domain.Member;
import com.dnd.bbok.saying.adapter.out.persistence.entity.BookmarkEntity;
import com.dnd.bbok.saying.adapter.out.persistence.entity.SayingEntity;
import com.dnd.bbok.saying.adapter.out.persistence.mapper.BookmarkMapper;
import com.dnd.bbok.saying.adapter.out.persistence.mapper.SayingMapper;
import com.dnd.bbok.saying.adapter.out.persistence.repository.BookmarkRepository;
import com.dnd.bbok.saying.application.port.out.BookmarkSayingPort;
import com.dnd.bbok.saying.application.port.out.LoadBookmarkPort;
import com.dnd.bbok.saying.domain.Bookmark;
import com.dnd.bbok.saying.domain.Saying;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BookmarkAdapter implements LoadBookmarkPort, BookmarkSayingPort {
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;
    private final MemberMapper memberMapper;
    private final SayingMapper sayingMapper;

    @Override
    public boolean isMarked(UUID memberId, Long sayingId) {
        Optional<BookmarkEntity> bookmarkEntity = bookmarkRepository.findByMemberIdAndSayingEntityId(memberId, sayingId);
        return bookmarkEntity.isPresent();
    }

    @Override
    public Bookmark checkBookmark(Saying saying, Member member) {
        BookmarkEntity bookmarkEntity = bookmarkRepository.findBookmark(saying.getId(), member.getId())
            .orElseThrow(() -> new BusinessException(ALREADY_BOOKMARKED));
        return bookmarkMapper.toDomain(bookmarkEntity, member, saying);
    }

    @Override
    public void saveBookmark(Bookmark bookmark) {
        MemberEntity memberEntity = memberMapper.toEntity(bookmark.getMember());
        SayingEntity sayingEntity = sayingMapper.toEntity(bookmark.getSaying());
        BookmarkEntity bookmarkEntity = bookmarkMapper.toEntity(bookmark, memberEntity,
            sayingEntity);
        bookmarkRepository.save(bookmarkEntity);
    }
}

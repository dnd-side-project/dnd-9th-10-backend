package com.dnd.bbok.saying.adapter.out.persistence.mapper;

import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
import com.dnd.bbok.member.domain.Member;
import com.dnd.bbok.saying.adapter.out.persistence.entity.BookmarkEntity;
import com.dnd.bbok.saying.adapter.out.persistence.entity.SayingEntity;
import com.dnd.bbok.saying.domain.Bookmark;
import com.dnd.bbok.saying.domain.Saying;
import org.springframework.stereotype.Component;

@Component
public class BookmarkMapper {

  public Bookmark toDomain(BookmarkEntity bookmarkEntity, Member member, Saying saying) {
    return new Bookmark(
        bookmarkEntity.getId(), member, saying);
  }

  public BookmarkEntity toEntity(Bookmark bookmark, MemberEntity member, SayingEntity saying) {
    return BookmarkEntity.builder()
        .id(bookmark.getId())
        .member(member)
        .saying(saying)
        .build();
  }
}

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

  public Bookmark toDomain(Member member, Saying saying) {
    return new Bookmark(null, member, saying);
  }

  public BookmarkEntity toEntity(MemberEntity member, SayingEntity saying) {
    return BookmarkEntity.builder()
        .member(member)
        .saying(saying)
        .build();
  }
}

package com.dnd.bbok.saying.adapter.out.persistence.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "bookmark")
public class BookmarkEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id")
  private MemberEntity member;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "saying_id")
  private SayingEntity saying;

  @Builder
  public BookmarkEntity(Long id, MemberEntity member, SayingEntity saying) {
    this.id = id;
    this.member = member;
    this.saying = saying;
  }

}

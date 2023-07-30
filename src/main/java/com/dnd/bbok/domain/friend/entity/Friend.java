package com.dnd.bbok.domain.friend.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;

import com.dnd.bbok.domain.common.BaseTimeEntity;
import com.dnd.bbok.domain.member.entity.Member;
import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class Friend extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private BbokCharacter bbok;

  @NotNull
  private String name;

  private boolean status;

  @NotNull
  private Long friendScore;

  /**
   * 친구와 회원을 다대일 관계 매핑
   */
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

}
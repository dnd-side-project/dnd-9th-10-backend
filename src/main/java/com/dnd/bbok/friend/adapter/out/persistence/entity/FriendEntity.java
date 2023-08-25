package com.dnd.bbok.friend.adapter.out.persistence.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import com.dnd.bbok.common.BaseTimeEntity;
import com.dnd.bbok.friend.domain.BbokCharacter;
import com.dnd.bbok.member.adapter.out.persistence.entity.MemberEntity;
import com.sun.istack.NotNull;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "friend")
public class FriendEntity extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private BbokCharacter bbok;

  @NotNull
  private String name;

  private boolean active;

  @NotNull
  private Long friendScore;

  /**
   * 친구와 회원을 다대일 관계 매핑
   */
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id")
  private MemberEntity member;

  @Builder
  public FriendEntity(Long id, BbokCharacter bbok, String name, boolean active,
      Long friendScore, MemberEntity member, LocalDateTime createdAt) {
    this.id = id;
    this.bbok = bbok;
    this.name = name;
    this.active = active;
    this.friendScore = friendScore;
    this.member = member;
    this.setCreatedAt(createdAt);
  }
}

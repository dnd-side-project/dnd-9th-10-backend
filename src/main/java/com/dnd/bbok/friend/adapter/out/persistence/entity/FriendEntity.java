package com.dnd.bbok.friend.adapter.out.persistence.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import com.dnd.bbok.domain.friend.entity.BbokCharacter;
import com.dnd.bbok.domain.member.entity.Member;
import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class FriendEntity {
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
  private Member member;

  public void changeFriendScore(Long friendScore) {
    this.friendScore = friendScore;
  }

  @Builder
  public FriendEntity(BbokCharacter bbok, String name, boolean active, Long friendScore, Member member) {
    this.bbok = bbok;
    this.name = name;
    this.active = active;
    this.friendScore = friendScore;
    this.member = member;
  }

}

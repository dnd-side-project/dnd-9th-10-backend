package com.dnd.bbok.friend.domain;

import com.dnd.bbok.member.domain.Member;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Friend {

  private Long id;

  private BbokCharacter bbok;

  private String name;

  private boolean active;

  private Long friendScore;

  private LocalDate createdAt;

  private Member member;

  @Builder
  public Friend(Long id, BbokCharacter bbok, String name,
      boolean active, Long friendScore, LocalDate createdAt, Member member) {
    this.id = id;
    this.bbok = bbok;
    this.name = name;
    this.active = active;
    this.friendScore = friendScore;
    this.createdAt = createdAt;
    this.member = member;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setStatus() {
    this.active = false;
  }
}

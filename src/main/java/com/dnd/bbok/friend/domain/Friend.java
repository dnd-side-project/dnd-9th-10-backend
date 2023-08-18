package com.dnd.bbok.friend.domain;

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

  @Builder
  public Friend(Long id, BbokCharacter bbok, String name,
      boolean active, Long friendScore, LocalDate createdAt) {
    this.id = id;
    this.bbok = bbok;
    this.name = name;
    this.active = active;
    this.friendScore = friendScore;
    this.createdAt = createdAt;
  }

}

package com.dnd.bbok.domain.diary.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;

import com.dnd.bbok.domain.common.BaseTimeEntity;
import com.dnd.bbok.domain.friend.entity.Friend;
import com.sun.istack.NotNull;
import java.time.LocalDate;
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
public class Diary extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private Emoji emoji;

  @NotNull
  private String contents;

  @NotNull
  private LocalDate diaryDate;

  /**
   * 일화와 친구를 다대일 관계 매핑
   */
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "friend_id")
  private Friend friend;

  @Builder
  public Diary(Emoji emoji, String contents, LocalDate diaryDate, Friend friend) {
    this.emoji = emoji;
    this.contents = contents;
    this.diaryDate = diaryDate;
    this.friend = friend;
  }
}

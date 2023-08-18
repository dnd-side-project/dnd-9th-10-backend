package com.dnd.bbok.diary.adapter.out.persistence.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;

import com.dnd.bbok.domain.common.BaseTimeEntity;
import com.dnd.bbok.diary.domain.Emoji;
import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendEntity;
import com.sun.istack.NotNull;
import java.time.LocalDate;
import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "diary")
public class DiaryEntity extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private Emoji emoji;

  @NotNull
  private String contents;

  @NotNull
  private LocalDate diaryDate;

  @NotNull
  private String sticker;

  /**
   * 일화와 친구를 다대일 관계 매핑
   */
  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "friend_id")
  private FriendEntity friend;

  @Builder
  public DiaryEntity(Emoji emoji, String contents, LocalDate diaryDate, FriendEntity friend, String sticker) {
    this.emoji = emoji;
    this.contents = contents;
    this.diaryDate = diaryDate;
    this.friend = friend;
    this.sticker = sticker;
  }
}

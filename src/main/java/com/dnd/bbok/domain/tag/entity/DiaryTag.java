package com.dnd.bbok.domain.tag.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import com.dnd.bbok.domain.diary.entity.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 다이어리가 가지고 있는 태그 정보
 */
@Entity
@Getter
@NoArgsConstructor
public class DiaryTag {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "diary_id")
  private Diary diary;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "tag_id")
  private FriendTag friendTag;

  @Builder
  public DiaryTag(Diary diary, FriendTag friendTag) {
    this.diary = diary;
    this.friendTag = friendTag;
  }
}

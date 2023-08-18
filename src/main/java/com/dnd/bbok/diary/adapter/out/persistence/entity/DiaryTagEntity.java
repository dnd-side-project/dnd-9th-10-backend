package com.dnd.bbok.diary.adapter.out.persistence.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import com.dnd.bbok.friend.adapter.out.persistence.entity.FriendTagEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 다이어리가 가지고 있는 태그 정보
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "diary_tag")
public class DiaryTagEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "diary_id")
  private DiaryEntity diaryEntity;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "tag_id")
  private FriendTagEntity friendTagEntity;

  @Builder
  public DiaryTagEntity(Long id, DiaryEntity diaryEntity, FriendTagEntity friendTagEntity) {
    this.id = id;
    this.diaryEntity = diaryEntity;
    this.friendTagEntity = friendTagEntity;
  }
}

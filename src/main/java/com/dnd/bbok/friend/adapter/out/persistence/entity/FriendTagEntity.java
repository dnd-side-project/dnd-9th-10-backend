package com.dnd.bbok.friend.adapter.out.persistence.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import com.dnd.bbok.diary.adapter.out.persistence.entity.DiaryTagEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * 친구가 가지고 있는 태그 정보
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "friend_tag")
public class FriendTagEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "friend_id")
  private FriendEntity friend;

  @NotNull
  private String name;

  @OneToMany(mappedBy = "friendTagEntity", cascade = CascadeType.ALL)
  private List<DiaryTagEntity> diaryTagEntities;

  @Builder
  public FriendTagEntity(String name, FriendEntity friend) {
    this.name = name;
    this.friend = friend;
  }
}

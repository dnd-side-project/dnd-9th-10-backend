package com.dnd.bbok.domain.tag.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import com.dnd.bbok.domain.friend.entity.Friend;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 친구가 가지고 있는 태그 정보
 */
@Entity
public class FriendTag {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "friend_id")
  private Friend friend;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "tag_id")
  private Tag tag;

}

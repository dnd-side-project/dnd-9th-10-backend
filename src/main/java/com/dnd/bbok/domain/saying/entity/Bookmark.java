package com.dnd.bbok.domain.saying.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import com.dnd.bbok.domain.member.entity.Member;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bookmark {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "saying_id")
  private Saying saying;

}

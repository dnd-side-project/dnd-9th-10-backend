package com.dnd.bbok.domain.checklist.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;

import com.dnd.bbok.domain.common.BaseTimeEntity;
import com.dnd.bbok.domain.member.entity.Member;
import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자가 입력한 기준
 */
@Entity
@Getter
@NoArgsConstructor
public class MemberChecklist extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  /**
   * @Column(nullable=false) 와 @NotNull의 차이점
   * https://kafcamus.tistory.com/15
   */
  @NotNull
  private String criteria;

  private boolean isGood;

  private boolean isUsed;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @Builder
  public MemberChecklist(Long id, String criteria, boolean isGood, boolean isUsed, Member member) {
    this.id = id;
    this.criteria = criteria;
    this.isGood = isGood;
    this.isUsed = isUsed;
    this.member = member;
  }
}

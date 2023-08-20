package com.dnd.bbok.member.adapter.out.persistence.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;

import com.dnd.bbok.common.BaseTimeEntity;
import com.sun.istack.NotNull;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자가 입력한 기준
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "member_checklist")
public class MemberChecklistEntity extends BaseTimeEntity {

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
  private MemberEntity member;

  @Builder
  public MemberChecklistEntity(Long id, String criteria, boolean isGood, boolean isUsed, MemberEntity member) {
    this.id = id;
    this.criteria = criteria;
    this.isGood = isGood;
    this.isUsed = isUsed;
    this.member = member;
  }

  public void updateChecklist(String criteria) {
    this.criteria = criteria;
  }
}

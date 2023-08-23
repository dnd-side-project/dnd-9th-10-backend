package com.dnd.bbok.diary.adapter.out.persistence.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import javax.persistence.*;

import com.dnd.bbok.member.adapter.out.persistence.entity.MemberChecklistEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 일기에서 사용중인 기준을 보여줍니다.
 *
 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "diary_checklist")
public class DiaryChecklistEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private boolean isChecked;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "diary_id")
  private DiaryEntity diaryEntity;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_checklist_id")
  private MemberChecklistEntity memberChecklistEntity;

  @Builder
  public DiaryChecklistEntity(Long id, boolean isChecked, DiaryEntity diaryEntity, MemberChecklistEntity memberChecklistEntity) {
    this.id = id;
    this.isChecked = isChecked;
    this.diaryEntity = diaryEntity;
    this.memberChecklistEntity = memberChecklistEntity;
  }
}

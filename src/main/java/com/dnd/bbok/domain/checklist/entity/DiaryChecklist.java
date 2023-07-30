package com.dnd.bbok.domain.checklist.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

import com.dnd.bbok.domain.diary.entity.Diary;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

/**
 * 일기에서 사용중인 기준을 보여줍니다.
 *
 */
@Entity
@Getter
public class DiaryChecklist {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  private boolean isChecked;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "diary_id")
  private Diary diary;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "member_checklist_id")
  private MemberChecklist memberChecklist;

}

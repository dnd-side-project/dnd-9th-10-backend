package com.dnd.bbok.domain.diary.entity;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.IDENTITY;

import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

/**
 * 스티커의 경우에는 다이어리에만 쓰기 때문에, Diary 폴더에 포함시켰습니다.
 */
@Entity
@Getter
public class DiarySticker {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private Sticker sticker;

  @NotNull
  private String position;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "diary_id")
  private Diary diary;

}

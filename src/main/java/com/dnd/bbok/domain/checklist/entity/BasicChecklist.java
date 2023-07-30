package com.dnd.bbok.domain.checklist.entity;

import static javax.persistence.GenerationType.IDENTITY;

import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;


/**
 * 기본 제공 기준(preSet)
 */
@Entity
@Getter
public class BasicChecklist {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotNull
  private String criteria;

  /**
   * Boolean이 아닌 boolean을 사용하는 이유
   * https://firstws.tistory.com/39
   * default는 false로 저장됨
   */
  private boolean isGood;

}

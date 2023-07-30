package com.dnd.bbok.domain.tag.entity;

import static javax.persistence.GenerationType.*;

import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 태그와 일화를 일대다, 다대일 매핑한다.
 * 친구Id를 외래키로 가지게 한다.
 */
@Entity
public class Tag {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotNull
  private String name;

}

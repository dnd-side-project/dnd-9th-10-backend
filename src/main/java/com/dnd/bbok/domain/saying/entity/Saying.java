package com.dnd.bbok.domain.saying.entity;

import static javax.persistence.GenerationType.*;

import com.dnd.bbok.domain.common.BaseTimeEntity;
import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Saying extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotNull
  private String contents;

  @NotNull
  private String reference;

}

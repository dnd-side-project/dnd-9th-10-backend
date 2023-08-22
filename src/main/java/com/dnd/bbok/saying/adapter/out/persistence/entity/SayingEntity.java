package com.dnd.bbok.saying.adapter.out.persistence.entity;

import static javax.persistence.GenerationType.*;

import com.dnd.bbok.common.BaseTimeEntity;
import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "saying")
public class SayingEntity extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @NotNull
  private String contents;

  @NotNull
  private String reference;

  public SayingEntity(Long id, String contents, String reference) {
    this.id = id;
    this.contents = contents;
    this.reference = reference;
  }

}

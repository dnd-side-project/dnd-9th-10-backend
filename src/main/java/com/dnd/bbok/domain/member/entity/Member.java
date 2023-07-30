package com.dnd.bbok.domain.member.entity;

import com.dnd.bbok.domain.common.BaseTimeEntity;
import com.github.f4b6a3.uuid.UuidCreator;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
public class Member extends BaseTimeEntity {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(length = 16)
  private UUID id;

  /**
   * id를 UUID로 저장했을 때의 단점(Long 보다 큰 용량을 차지)을 해결하기 위한 조치
   * https://velog.io/@jsb100800/Spring-boot-JPA-PK-MAPPING
   * https://cano721.tistory.com/214
   */
  @PrePersist
  public void createId() {
    this.id = UuidCreator.getTimeOrdered();
  }

}

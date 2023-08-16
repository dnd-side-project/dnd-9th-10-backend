package com.dnd.bbok.member.adapter.out.persistence.entity;

import com.dnd.bbok.domain.common.BaseTimeEntity;
import com.github.f4b6a3.uuid.UuidCreator;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
public class MemberEntity extends BaseTimeEntity {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(length = 16)
  private UUID id;

  /**
   * 역할(Guest, Social)
   */
  @Enumerated(EnumType.STRING)
  private Role role;

  /**
   * 회원번호 (oauthProvider#회원번호)
   * 게스트 로그인한 사용자의 이름을 나타내기 위해서 생성했습니다.
   */
  private String userNumber;

  /**
   * SNS 로그인한 사용자의 이름
   * 게스트 로그인 사용자의 이름은 NONE으로 설정했습니다.
   */
  private String username;

  /**
   * profile 이미지 url
   */
  private String profileUrl;

  /**
   * OAuth 제공자
   */
  @Enumerated(EnumType.STRING)
  private OAuth2Provider oauth2Provider;

  @PrePersist
  public void createId() {
    this.id = UuidCreator.getTimeOrdered();
  }
}

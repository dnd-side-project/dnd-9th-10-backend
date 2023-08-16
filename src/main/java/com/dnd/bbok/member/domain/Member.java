package com.dnd.bbok.member.domain;

import com.dnd.bbok.member.adapter.out.persistence.entity.OAuth2Provider;
import com.dnd.bbok.member.adapter.out.persistence.entity.Role;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

  private final UUID id;

  private final Role role;

  /**
   * OAuth 제공자
   */
  private final OAuth2Provider oAuth2Provider;

  /**
   * 회원번호 (oauthProvider#회원번호)
   */
  private final String userNumber;

  /**
   * SNS 로그인한 사용자의 이름
   */
  private final String username;

  /**
   * profile 이미지 url
   */
  private final String profileUrl;

  @Builder
  public Member(UUID id, Role role, String userNumber,
      String username, String profileUrl, OAuth2Provider oAuth2Provider) {
    this.id = id;
    this.role = role;
    this.userNumber = userNumber;
    this.username = username;
    this.profileUrl = profileUrl;
    this.oAuth2Provider = oAuth2Provider;
  }

}

package com.dnd.bbok.domain.member.dto.response;

import com.dnd.bbok.domain.member.entity.Member;
import com.dnd.bbok.domain.member.entity.OAuth2Provider;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class MemberInfoResponseDto {
  @ApiModelProperty("member의 프로필 이미지 url")
  private final String profileUrl;

  @ApiModelProperty("member의 고유 Id")
  private final String memberId;

  @ApiModelProperty("member의 이름")
  private final String memberName;

  @ApiModelProperty("member의 oauth 제공자(GUEST, KAKAO)")
  private final OAuth2Provider oauth2Provider;

  public MemberInfoResponseDto(Member member) {
    this.profileUrl = member.getProfileUrl();
    this.memberId = member.getId().toString();
    this.memberName =
        member.getOauth2Provider().equals(OAuth2Provider.GUEST) ?
        member.getUserNumber():member.getUsername();
    this.oauth2Provider = member.getOauth2Provider();
  }

}

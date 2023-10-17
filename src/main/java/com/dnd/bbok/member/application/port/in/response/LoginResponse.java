package com.dnd.bbok.member.application.port.in.response;

import com.dnd.bbok.member.domain.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class LoginResponse {

  @ApiModelProperty("발급된 액세스 토큰")
  private final String accessToken;

  @ApiModelProperty("발급된 리프레쉬 토큰")
  private final String refreshToken;

  @ApiModelProperty("멤버의 id")
  private final String memberId;

  @ApiModelProperty("oauth provider")
  private final String provider;

  @ApiModelProperty("멤버의 프로필 url")
  private final String profileUrl;

  public LoginResponse(String accessToken, String refreshToken, Member member) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.memberId = member.getId().toString();
    this.provider = member.getOAuth2Provider().toString();
    this.profileUrl = member.getProfileUrl();
  }

}

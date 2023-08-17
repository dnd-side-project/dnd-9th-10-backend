package com.dnd.bbok.member.application.port.in.response;

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

  public LoginResponse(String accessToken, String refreshToken, String memberId) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.memberId = memberId;
  }

}

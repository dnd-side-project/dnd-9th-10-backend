package com.dnd.bbok.domain.member.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class LoginResponseDto {

  @ApiModelProperty("발급된 액세스 토큰")
  private final String accessToken;

  @ApiModelProperty("발급된 리프레쉬 토큰")
  private final String refreshToken;

  @ApiModelProperty("멤버의 id")
  private final String memberId;

  public LoginResponseDto(String accessToken, String refreshToken, String memberId) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.memberId = memberId;
  }

}

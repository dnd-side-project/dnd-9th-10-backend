package com.dnd.bbok.member.application.port.in.response;

import lombok.Getter;

@Getter
public class ReIssueToken {

  private final String refreshToken;

  private final String accessToken;

  public ReIssueToken(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }

}

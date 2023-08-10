package com.dnd.bbok.domain.jwt.dto;

import lombok.Getter;

@Getter
public class ReIssueTokenDto {

  private final String refreshToken;

  private final String accessToken;

  public ReIssueTokenDto(String accessToken, String refreshToken) {
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
  }
}

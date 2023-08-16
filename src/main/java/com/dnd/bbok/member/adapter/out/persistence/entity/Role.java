package com.dnd.bbok.member.adapter.out.persistence.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

  ROLE_GUEST("guest"),
  ROLE_SOCIAL("social");

  private final String authority;

}


package com.dnd.bbok.member.application.port.in.response;

import com.dnd.bbok.member.domain.Member;
import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SessionUser implements Serializable {

  private final UUID uuid;
  private final String authority;

  public SessionUser(Member member) {
    this.uuid = member.getId();
    this.authority = member.getRole().getAuthority();
  }

}


package com.dnd.bbok.member.application.port.in.usecase;

import org.springframework.security.core.Authentication;

public interface GetAuthQuery {

  Authentication getAuthentication(String accessToken);

}

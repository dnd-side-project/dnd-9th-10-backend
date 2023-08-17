package com.dnd.bbok.member.application.port.in.usecase;

import com.dnd.bbok.member.application.port.in.response.ReIssueToken;

public interface TokenReissueUseCase {

  ReIssueToken reIssueToken(String refreshToken);

}

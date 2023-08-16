package com.dnd.bbok.member.application.port.in;

import java.util.UUID;

public interface GetMemberQuery {

  GetMemberInfoResponse getMember(UUID memberId);

}

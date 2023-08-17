package com.dnd.bbok.member.application.port.in.usecase;

import com.dnd.bbok.member.application.port.in.response.GetMemberInfoResponse;
import java.util.UUID;

public interface GetMemberQuery {

  GetMemberInfoResponse getMember(UUID memberId);

}

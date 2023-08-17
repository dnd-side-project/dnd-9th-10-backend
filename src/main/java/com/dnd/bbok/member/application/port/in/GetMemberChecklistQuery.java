package com.dnd.bbok.member.application.port.in;

import java.util.UUID;

public interface GetMemberChecklistQuery {
    GetMemberChecklistResponse getMemberChecklistQuery(UUID memberId);
}

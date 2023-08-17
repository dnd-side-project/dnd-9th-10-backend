package com.dnd.bbok.member.application.port.in.usecase;

import com.dnd.bbok.member.application.port.in.response.GetMemberChecklistResponse;

import java.util.UUID;

public interface GetMemberChecklistQuery {
    GetMemberChecklistResponse getMemberChecklistQuery(UUID memberId);
}

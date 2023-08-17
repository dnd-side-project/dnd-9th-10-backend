package com.dnd.bbok.member.application.port.out;

import com.dnd.bbok.member.domain.MemberChecklist;

import java.util.UUID;

public interface LoadMemberChecklistPort {
    MemberChecklist loadMemberChecklist(UUID memberId);
}

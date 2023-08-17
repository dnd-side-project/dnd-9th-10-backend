package com.dnd.bbok.member.application.port.out;

import com.dnd.bbok.member.domain.MemberChecklist;

import java.util.UUID;

public interface SaveMemberChecklistPort {
    void saveMemberChecklist(UUID memberId, MemberChecklist memberChecklist);
}

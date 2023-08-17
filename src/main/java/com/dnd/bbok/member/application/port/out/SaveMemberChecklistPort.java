package com.dnd.bbok.member.application.port.out;

import com.dnd.bbok.member.domain.MemberChecklist;

import java.util.UUID;

public interface SaveMemberChecklistPort {
    void save(UUID memberId, MemberChecklist memberChecklist);
}

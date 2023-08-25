package com.dnd.bbok.member.application.port.out;

import com.dnd.bbok.member.domain.ChecklistInfo;
import com.dnd.bbok.member.domain.MemberChecklist;

import java.util.List;
import java.util.UUID;

public interface SaveMemberChecklistPort {
    void saveMemberChecklist(UUID memberId, MemberChecklist memberChecklist);

    void saveMemberChecklistWithCond(UUID memberId, List<ChecklistInfo> newChecklist);
}

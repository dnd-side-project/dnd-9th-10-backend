package com.dnd.bbok.domain.checklist.service;


import com.dnd.bbok.domain.checklist.entity.MemberChecklist;
import com.dnd.bbok.domain.checklist.repository.MemberChecklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberChecklistEntityService {
    private final MemberChecklistRepository memberChecklistRepository;

    public void createMemberChecklist(List<MemberChecklist> checklist) {
        memberChecklistRepository.saveAll(checklist);
    }

    public List<MemberChecklist> getMemberChecklistInIds(List<Long> ids) {
        return this.memberChecklistRepository.findByIdIn(ids);
    }
}

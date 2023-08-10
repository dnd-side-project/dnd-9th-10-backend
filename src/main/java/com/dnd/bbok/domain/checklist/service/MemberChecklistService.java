package com.dnd.bbok.domain.checklist.service;


import com.dnd.bbok.domain.checklist.entity.MemberChecklist;
import com.dnd.bbok.domain.checklist.repository.MemberChecklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberChecklistService {
    private final MemberChecklistRepository memberChecklistRepository;

    public List<MemberChecklist> createMemberChecklist(List<MemberChecklist> checklist) {
        return memberChecklistRepository.saveAll(checklist);
    }
}

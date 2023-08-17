package com.dnd.bbok.domain.checklist.service;


import com.dnd.bbok.member.adapter.out.persistence.entity.MemberChecklistEntity;
import com.dnd.bbok.member.adapter.out.persistence.repository.MemberChecklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberChecklistEntityService {
    private final MemberChecklistRepository memberChecklistRepository;


    public List<MemberChecklistEntity> getMemberChecklistInIds(List<Long> ids) {
        return this.memberChecklistRepository.findByIdIn(ids);
    }
}

package com.dnd.bbok.basicchecklist.adapter.out.persistence;

import com.dnd.bbok.basicchecklist.adapter.out.persistence.mapper.BasicChecklistMapper;
import com.dnd.bbok.basicchecklist.adapter.out.persistence.repository.BasicChecklistRepository;
import com.dnd.bbok.basicchecklist.application.port.out.LoadBasicChecklistPort;
import com.dnd.bbok.basicchecklist.domain.BasicChecklist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BasicChecklistPersistenceAdapter implements LoadBasicChecklistPort {
    private final BasicChecklistRepository basicChecklistRepository;
    private final BasicChecklistMapper basicChecklistMapper;

    @Override
    public BasicChecklist load() {
        return basicChecklistMapper.toDomain(basicChecklistRepository.findAll());
    }
}

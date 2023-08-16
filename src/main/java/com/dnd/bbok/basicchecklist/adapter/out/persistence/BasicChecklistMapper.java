package com.dnd.bbok.basicchecklist.adapter.out.persistence;

import com.dnd.bbok.basicchecklist.domain.BasicChecklist;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BasicChecklistMapper {
    public BasicChecklist toDomain(List<BasicChecklistEntity> checklistEntities) {
        List<String> goodChecklist = checklistEntities.stream().filter(BasicChecklistEntity::isGood).map(BasicChecklistEntity::getCriteria).collect(Collectors.toList());
        List<String> badChecklist = checklistEntities.stream().filter(ele -> !ele.isGood()).map(BasicChecklistEntity::getCriteria).collect(Collectors.toList());

        return new BasicChecklist(goodChecklist, badChecklist);
    }
}
